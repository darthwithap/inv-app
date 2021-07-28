package me.darthwithap.invapp.ui.invoice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import es.dmoral.toasty.Toasty
import me.darthwithap.api.models.entities.data.ProductData
import me.darthwithap.invapp.R
import me.darthwithap.invapp.data.domain.models.Godown
import me.darthwithap.invapp.data.domain.models.StockItem
import me.darthwithap.invapp.databinding.FragmentInvoiceBinding
import me.darthwithap.invapp.ui.viewmodel.GodownViewModel
import me.darthwithap.invapp.ui.viewmodel.InvoiceViewModel
import me.darthwithap.invapp.ui.viewmodel.StockViewModel
import me.darthwithap.invapp.utils.extensions.afterTextChanged

private const val TAG = "InvoiceFragment"

class InvoiceFragment : Fragment() {

    private lateinit var invoiceViewModel: InvoiceViewModel
    private lateinit var stockViewModel: StockViewModel
    private lateinit var godownViewModel: GodownViewModel
    private var _binding: FragmentInvoiceBinding? = null
    private var godownId: String? = null
    private var godownSelected: Boolean = false
    private var isDataValid: Boolean = false
    private var godownError: String? = null
    private var productsError: String? = null

    private lateinit var invoiceProductAdapter: InvoiceProductAdapter
    private lateinit var autocompleteAdapter: SearchAutocompleteAdapter
    private lateinit var godownDropdownAdapter: GodownDropdownAdapter
    private lateinit var loader: ProgressBar

    private val godowns: ArrayList<Godown> = arrayListOf()
    private val godownItems: ArrayList<GodownItem> = arrayListOf()
    private val suggestions: ArrayList<StockItem> = arrayListOf()
    private val invoiceProducts: ArrayList<ProductData> = arrayListOf()

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        invoiceViewModel = ViewModelProvider(this).get(InvoiceViewModel::class.java)
        stockViewModel = ViewModelProvider(this).get(StockViewModel::class.java)
        godownViewModel = ViewModelProvider(this).get(GodownViewModel::class.java)

        godownViewModel.getAllGodowns()

        _binding = FragmentInvoiceBinding.inflate(inflater, container, false)

        loader = _binding?.loadingBar!!

        autocompleteAdapter =
            SearchAutocompleteAdapter(
                requireContext(),
                R.layout.invoice_product_autocomplete_row,
                suggestions
            )

        invoiceProductAdapter = InvoiceProductAdapter(autocompleteAdapter,
            // set stock id on itemClick for autocomplete text view
            { pos, stock ->
                invoiceProducts[pos].stock = stock
            },
            // set qty on edit text qty text changed
            { pos, qty ->
                if (qty.isNotBlank()) invoiceProducts[pos].quantity = qty.toInt()
            },
            // search stock on autocomplete textview text changed
            {
                if (it.isNotBlank() && it.trim().length < 8) {
                    stockViewModel.searchStock(it)
                }
            },
            // autocomplete textview editor action listener
            { id, pos, stock ->
                when (id) {
                    EditorInfo.IME_ACTION_NEXT -> {
                        invoiceProducts[pos].stock = stock
                    }
                }
                false
            })
        // edit text quantity textview editor action listener
        { id, pos, qty ->
            when (id) {
                EditorInfo.IME_ACTION_NEXT -> {
                    if (qty.isNotBlank()) invoiceProducts[pos].quantity = qty.toInt()
                    // add next item to invoiceProducts list only if current edit text is last in the row
                    if (invoiceProducts.size - 1 == pos) {
                        godownId?.let {
                            invoiceViewModel.formDataChanged(
                                _binding?.etCustomerName?.text.toString(),
                                godownId,
                                getFilteredList()
                            )
                            addProductData(it)
                        }
                    }
                }
            }
            false
        }

        _binding?.etCustomerName?.requestFocus()

        godownDropdownAdapter =
            GodownDropdownAdapter(requireContext(), R.layout.dropdown_item_godown, godownItems)

        _binding?.spinnerGodowns?.adapter = godownDropdownAdapter

        _binding?.rvInvoiceProductItems?.layoutManager = LinearLayoutManager(context)
        _binding?.rvInvoiceProductItems?.adapter = invoiceProductAdapter

        invoiceProductAdapter.submitList(invoiceProducts)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.etCustomerName?.afterTextChanged {
            invoiceViewModel.formDataChanged(it, godownId, getFilteredList())
        }

        invoiceViewModel.invoiceFormState.observe(viewLifecycleOwner, {
            _binding?.btnInvoiceDone?.isEnabled = it.isDataValid
            isDataValid = it.isDataValid

            if (it.customerError != null) {
                _binding?.etCustomerName?.error = getString(it.customerError)
            }
            if (it.productsError != null) {
                productsError = getString(it.productsError)
            }
            if (it.godownError != null) {
                godownError = getString(it.godownError)
            }
        })

        invoiceViewModel.createInvoiceResult.observe(viewLifecycleOwner, {
            loader.visibility = View.GONE

            if (it.error != null) {
                showError(it.error)
            }
            if (it.success != null) {
                showSuccess("Invoice created id: ${it.success}")
            }
        })

        _binding?.spinnerGodowns?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val godownItem = parent?.selectedItem as GodownItem
                    godownId = godownItem.id
                    invoiceViewModel.formDataChanged(
                        _binding?.etCustomerName?.text.toString(),
                        godownId,
                        getFilteredList()
                    )
                    if (!godownSelected) godownId?.let {
                        addProductData(it)
                    }
                    godownSelected = godownId != "-1"
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    godownSelected = false
                }
            }

        godownViewModel.godownsResult.observe(viewLifecycleOwner, {
            //loading.visibility = View.GONE
            if (it.error != null) {
                showError(it.error)
            }
            if (it.success != null) {
                updateGodownsDropdown(it.success)
            }
        })

        stockViewModel.searchStockResult.observe(viewLifecycleOwner, {
            if (it.error != null) {
                showError(it.error)
            }
            if (it.success != null) {
                updateActvAdapter(it.success)
            }
        })

        _binding?.btnInvoiceDone?.setOnClickListener {
            if (isDataValid) {
                loader.visibility = View.VISIBLE
                invoiceViewModel.createInvoice(
                    _binding?.etCustomerName?.text.toString(),
                    getFilteredList()
                )
            } else if (godownError != null) showError(godownError!!)
            else if (productsError != null) showError(productsError!!)
            else showError("Invalid form data")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getFilteredList(): List<ProductData> {
        return invoiceProducts.filter {
            (it.quantity > 0 && it.stock.isNotBlank())
        }
    }

    private fun showSuccess(success: String) {
        context?.let { Toasty.success(it, success).show() }
    }

    private fun addProductData(godownId: String) {
        if (godownId != "-1") {
            invoiceProducts.add(ProductData(godownId, 0, ""))
            invoiceProductAdapter.notifyItemInserted(invoiceProducts.size - 1)
        }
    }

    private fun updateGodownsDropdown(list: List<Godown>) {
        godowns.clear()
        godowns.addAll(list)

        godownItems.clear()
        godownItems.add(GodownItem("-1", "Choose a godown"))
        val items = godowns.map { godown ->
            with(godown) {
                GodownItem(godownId, name)
            }
        } as ArrayList<GodownItem>
        godownItems.addAll(items)
        godownDropdownAdapter.notifyDataSetChanged()
    }

    private fun updateActvAdapter(list: List<StockItem>) {
        Log.d(TAG, "updateActvAdapter: list: $list")
        autocompleteAdapter.clear()
        autocompleteAdapter.addAll(list)
        //autocompleteAdapter.notifyDataSetChanged()
    }

    private fun showError(error: String) {
        context?.let { Toasty.error(it, error).show() }
    }
}