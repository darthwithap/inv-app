package me.darthwithap.invapp.ui.sales

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import es.dmoral.toasty.Toasty
import me.darthwithap.api.models.entities.dto.SalesInvoiceDto
import me.darthwithap.invapp.databinding.FragmentSalesBinding
import me.darthwithap.invapp.ui.viewmodel.SalesViewModel

class SalesFragment : Fragment() {

    private lateinit var salesViewModel: SalesViewModel
    private var _binding: FragmentSalesBinding? = null
    private val binding get() = _binding
    private val sales: ArrayList<SalesInvoiceDto> = arrayListOf()
    private lateinit var saleAdapter: SaleAdapter

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
        salesViewModel.salesResult.observe(viewLifecycleOwner, {
            //loading.visibility = View.GONE
            if (it.error != null) {
                showError(it.error)
            }
            if (it.success != null) {
                updateUi(it.success)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUi(list: List<SalesInvoiceDto>) {
        sales.clear()
        sales.addAll(list)
        saleAdapter.notifyDataSetChanged()
    }

    private fun showError(error: String) {
        context?.let { Toasty.error(it, error).show() }
    }
}