package me.darthwithap.invapp.ui.addstock.godownstock

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import es.dmoral.toasty.Toasty
import me.darthwithap.invapp.data.domain.models.Stock
import me.darthwithap.invapp.databinding.FragmentGodownAddStockBinding
import me.darthwithap.invapp.ui.viewmodel.StockViewModel
import me.darthwithap.invapp.utils.extensions.afterTextChanged

private const val TAG = "GodownAddStockFragment"

class GodownAddStockFragment : Fragment() {

    private var _binding: FragmentGodownAddStockBinding? = null
    private lateinit var stockViewModel: StockViewModel
    private val binding get() = _binding

    private lateinit var code: EditText
    private lateinit var name: EditText
    private lateinit var range: EditText
    private lateinit var brand: EditText
    private lateinit var quantity: EditText

    private lateinit var addStockButton: Button
    private lateinit var loading: ProgressBar

    private var isDataValid = false
    private var isButtonClick = false
    private val args: GodownAddStockFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        stockViewModel = ViewModelProvider(this).get(StockViewModel::class.java)
        _binding = FragmentGodownAddStockBinding.inflate(layoutInflater, container, false)

        _binding?.tvGodownNameHeading?.text = args.godownName

        code = _binding?.etProductCode!!
        name = _binding?.etProductName!!
        range = _binding?.etProductRange!!
        brand = _binding?.etProductBrand!!
        quantity = _binding?.etQuantity!!
        loading = _binding?.loadingBar!!
        addStockButton = _binding?.btnAddStock!!

        stockViewModel.addStockFormState.observe(viewLifecycleOwner, {
            addStockButton.isEnabled = it.isDataValid
            isDataValid = it.isDataValid

            Log.d(TAG, "onCreateView: from viewmodel: ${it.isDataValid}")
            Log.d(TAG, "onCreateView isDataValid: $isDataValid")


            if (it.nameError != null) {
                name.error = getString(it.nameError)
            }
            if (it.quantityError != null) {
                quantity.error = getString(it.quantityError)
            }
        })

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stockViewModel.addStockResult.observe(viewLifecycleOwner, {

            loading.visibility = View.GONE
            if (it.error != null) {
                showError(it.error)
            }
            if (it.success != null) {
                updateUi(it.success)
            }
        })

        name.afterTextChanged {
            quantity.text.toString().takeIf { it.isNotBlank() }?.let { qty ->
                stockViewModel.formDataChanged(
                    name.text.toString(),
                    qty.toInt(),
                )
            }
        }

        quantity.apply {
            afterTextChanged {
                text.toString().takeIf { it.isNotBlank() }?.let { qty ->
                    stockViewModel.formDataChanged(
                        name.text.toString(),
                        qty.toInt(),
                    )
                }
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        if (isDataValid) {
                            loading.visibility = View.VISIBLE
                            isButtonClick = true
                            quantity.text.toString().takeIf { it.isNotBlank() }?.let { qty ->
                                stockViewModel.addStock(
                                    args.godownId,
                                    name.text.toString(),
                                    qty.toInt(),
                                    brand.text.toString().takeIf { it.isNotBlank() },
                                    code.text.toString().takeIf { it.isNotBlank() },
                                    range.text.toString().takeIf { it.isNotBlank() }
                                )
                            }
                        } else showError("Fields cant be empty")
                    }
                }
                false
            }
        }

        addStockButton.setOnClickListener {
            if (isDataValid) {
                loading.visibility = View.VISIBLE
                isButtonClick = true
                quantity.text.toString().takeIf { it.isNotBlank() }?.let { qty ->
                    stockViewModel.addStock(
                        args.godownId,
                        name.text.toString(),
                        qty.toInt(),
                        brand.text.toString().takeIf { it.isNotBlank() },
                        code.text.toString().takeIf { it.isNotBlank() },
                        range.text.toString().takeIf { it.isNotBlank() }
                    )
                }
            } else showError("Fields cannot be empty")
        }

        _binding?.llCheckStock?.setOnClickListener {
            val action =
                GodownAddStockFragmentDirections.actionNavGodownAddStockToCheckStock(args.godownId)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showError(errorString: String) {
        Toasty.error(requireContext(), errorString).show()
    }

    private fun updateUi(stock: Stock) {
        if (isButtonClick) {
            Toasty.success(
                requireContext(),
                "${stock.quantity} ${stock.name} added",
            ).show()
        }
        isButtonClick = false
    }

}