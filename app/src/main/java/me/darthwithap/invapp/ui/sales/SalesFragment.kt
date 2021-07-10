package me.darthwithap.invapp.ui.sales

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import me.darthwithap.invapp.data.models.Sale
import me.darthwithap.invapp.databinding.FragmentSalesBinding
import me.darthwithap.invapp.ui.addstock.GodownAdapter

class SalesFragment : Fragment() {

    private lateinit var salesViewModel: SalesViewModel
    private var _binding: FragmentSalesBinding? = null
    private val binding get() = _binding

    private lateinit var saleAdapter: SaleAdapter
    private val sales: ArrayList<Sale> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        salesViewModel =
            ViewModelProvider(this).get(SalesViewModel::class.java)

        salesViewModel.getSales()
        _binding = FragmentSalesBinding.inflate(inflater, container, false)

        _binding?.rvSales?.layoutManager = LinearLayoutManager(context)
        saleAdapter = SaleAdapter(sales)
        _binding?.rvSales?.adapter = saleAdapter

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        salesViewModel.sales.observe(viewLifecycleOwner, {
            sales.clear()
            sales.addAll(it)
            saleAdapter.notifyDataSetChanged()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}