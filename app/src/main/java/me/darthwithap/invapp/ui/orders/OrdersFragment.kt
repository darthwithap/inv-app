package me.darthwithap.invapp.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import me.darthwithap.invapp.data.models.Godown
import me.darthwithap.invapp.databinding.FragmentOrdersBinding
import me.darthwithap.invapp.ui.GodownViewModel

class OrdersFragment : Fragment() {

    private lateinit var godownViewModel: GodownViewModel
    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding

    private lateinit var godownAdapter: GodownChipAdapter
    private lateinit var godownOrdersAdapter: GodownOrderAdapter
    private val godowns: ArrayList<Godown> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        godownViewModel =
            ViewModelProvider(this).get(GodownViewModel::class.java)
        godownViewModel.getGodowns()
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)

        // Godown Chip RecyclerView Setup
        _binding?.rvGodownChips?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        godownAdapter = GodownChipAdapter(godowns)
        _binding?.rvGodownChips?.adapter = godownAdapter

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        godownViewModel.godowns.observe(viewLifecycleOwner, {
            godowns.clear()
            godowns.addAll(it)
            godownAdapter.notifyDataSetChanged()
            updateGodownOrders()
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
}