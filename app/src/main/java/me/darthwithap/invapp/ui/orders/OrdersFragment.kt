package me.darthwithap.invapp.ui.orders

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import es.dmoral.toasty.Toasty
import me.darthwithap.invapp.data.domain.models.Godown
import me.darthwithap.invapp.data.domain.models.Invoice
import me.darthwithap.invapp.databinding.FragmentOrdersBinding
import me.darthwithap.invapp.ui.orders.adapter.GodownChipAdapter
import me.darthwithap.invapp.ui.orders.adapter.GodownOrderAdapter
import me.darthwithap.invapp.ui.viewmodel.GodownViewModel
import me.darthwithap.invapp.ui.viewmodel.InvoiceViewModel

private const val TAG = "TAG"

class OrdersFragment : Fragment() {

    private lateinit var godownViewModel: GodownViewModel
    private lateinit var invoiceViewModel: InvoiceViewModel
    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding

    private var godownIndex = 0
    private var globalCheckCount = 0

    private lateinit var godownChipAdapter: GodownChipAdapter
    private lateinit var godownOrdersAdapter: GodownOrderAdapter
    private val godowns: ArrayList<Godown> = arrayListOf()
    private val invoices: ArrayList<Invoice> = arrayListOf()
    private var currGodownId: String = ""
    private val pendingOrdersDataMap: HashMap<String, ArrayList<String>> = hashMapOf()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        godownViewModel =
            ViewModelProvider(this).get(GodownViewModel::class.java)
        invoiceViewModel =
            ViewModelProvider(this).get(InvoiceViewModel::class.java)

        godownViewModel.getAllGodowns()

        _binding = FragmentOrdersBinding.inflate(inflater, container, false)

        // Godown Chip RecyclerView Setup
        _binding?.rvGodownChips?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        godownChipAdapter = GodownChipAdapter(godowns) { id, _ ->
            godownViewModel.onCurrentGodownChanged(id)
        }

        godownOrdersAdapter = GodownOrderAdapter(invoices) { _, isChecked, productId, invoiceId ->
            pendingOrdersDataMap.getOrPut(invoiceId, { arrayListOf() })
            if (isChecked) {
                globalCheckCount++
                pendingOrdersDataMap[invoiceId]?.add(productId)
            } else {
                globalCheckCount--
                pendingOrdersDataMap[invoiceId]?.removeIf { it == productId }
            }
            Log.d(TAG, "onCreateView: $pendingOrdersDataMap")
        }
        _binding?.rvGodownOrders?.adapter = godownOrdersAdapter

        _binding?.rvGodownChips?.adapter = godownChipAdapter

        _binding?.btnDone?.setOnClickListener {
            if (pendingOrdersDataMap.isNotEmpty()) {
                pendingOrdersDataMap.forEach {
                    if (it.value.isNotEmpty()) {
                        invoiceViewModel.updatePendingOrdersStatusFor(it.key, it.value)
                    }
                }
            } else {
                showError("No products set as delivered")
            }
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        godownViewModel.godownsResult.observe(viewLifecycleOwner, {
            //loading.visibility = View.GONE
            if (it.error != null) {
                showError(it.error)
            }
            if (it.success != null) {
                updateUi(it.success)
            }
        })

        godownViewModel.currGodownId.observe(viewLifecycleOwner, {
            if (currGodownId != it) godownChipAdapter.setCurrentGodown(it)
            godownChipAdapter.notifyDataSetChanged()
            invoiceViewModel.getPendingOrdersForGodown(it)
            pendingOrdersDataMap.clear()
        })

        invoiceViewModel.pendingOrdersResult.observe(viewLifecycleOwner, {
            if (it.error != null) {
                showError(it.error)
            }
            if (it.success != null) {
                updateGodownOrders(it.success)
            }
        })

        invoiceViewModel.pendingOrdersUpdateStatusResult.observe(viewLifecycleOwner, {
            if (it.error != null) {
                showError(it.error)
            }
            if (it.success != null) {
                showSuccess(it.success)
            }
        })
    }

    private fun updateGodownOrders(list: List<Invoice>) {
        _binding?.rvGodownOrders?.layoutManager = LinearLayoutManager(context)
        invoices.clear()
        invoices.addAll(list)
        godownOrdersAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUi(list: List<Godown>) {
        godownViewModel.onCurrentGodownChanged(list[godownIndex].godownId)
        godowns.clear()
        godowns.addAll(list)
        godownChipAdapter.notifyDataSetChanged()
    }

    private fun showError(error: String) {
        context?.let { Toasty.error(it, error).show() }
    }

    private fun showSuccess(success: String) {
        context?.let { Toasty.success(it, success).show() }
    }
}