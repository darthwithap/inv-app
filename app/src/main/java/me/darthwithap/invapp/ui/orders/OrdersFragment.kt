package me.darthwithap.invapp.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import es.dmoral.toasty.Toasty
import me.darthwithap.invapp.data.domain.models.Godown
import me.darthwithap.invapp.databinding.FragmentOrdersBinding
import me.darthwithap.invapp.ui.viewmodel.GodownViewModel
import me.darthwithap.invapp.ui.viewmodel.InvoiceViewModel

class OrdersFragment : Fragment() {

    private lateinit var godownViewModel: GodownViewModel
    private lateinit var invoiceViewModel: InvoiceViewModel
    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding

    private lateinit var godownChipAdapter: GodownChipAdapter
    private lateinit var godownOrdersAdapter: GodownOrderAdapter
    private val godowns: ArrayList<Godown> = arrayListOf()

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
        godownChipAdapter = GodownChipAdapter(godowns) {
            invoiceViewModel
        }
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

    }

    private fun updateGodownOrders() {
        _binding?.rvGodownOrders?.layoutManager = LinearLayoutManager(context)
        godownOrdersAdapter = godowns[0].orders?.let { GodownOrderAdapter(it) }!!
        _binding?.rvGodownOrders?.adapter = godownOrdersAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUi(list: List<Godown>) {
        godowns.clear()
        godowns.addAll(list)
        godownChipAdapter.notifyDataSetChanged()
        updateGodownOrders()
    }

    private fun showError(error: String) {
        context?.let { Toasty.error(it, error).show() }
    }
}