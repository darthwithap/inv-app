package me.darthwithap.invapp.ui.orders

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import kotlin.math.log

private const val TAG = "OrdersFragment"

class OrdersFragment : Fragment() {

    private lateinit var godownViewModel: GodownViewModel
    private lateinit var invoiceViewModel: InvoiceViewModel
    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding

    private var godownIndex = 0

    private lateinit var godownChipAdapter: GodownChipAdapter
    private lateinit var godownOrdersAdapter: GodownOrderAdapter
    private val godowns: ArrayList<Godown> = arrayListOf()
    private val invoices: ArrayList<Invoice> = arrayListOf()

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

        godownOrdersAdapter = GodownOrderAdapter(invoices) { buttonView, isChecked ->
            if (buttonView.isChecked) {
                Log.d(TAG, "onCreateView: buttonView.isChecked: true")
            } else
                Log.d(TAG, "onCreateView: buttonView.isChecked: false")
            if (isChecked) Log.d(TAG, "onCreateView: isChecked: true")
            else Log.d(TAG, "onCreateView: isChecked: false")
        }
        _binding?.rvGodownOrders?.adapter = godownOrdersAdapter

        _binding?.rvGodownChips?.adapter = godownChipAdapter

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
            invoiceViewModel.getPendingOrdersForGodown(it)
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