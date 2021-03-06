package me.darthwithap.invapp.ui.addstock.godownstock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import es.dmoral.toasty.Toasty
import me.darthwithap.invapp.R
import me.darthwithap.invapp.data.domain.models.Stock
import me.darthwithap.invapp.databinding.FragmentGodownCheckStockBinding
import me.darthwithap.invapp.ui.viewmodel.StockViewModel

class GodownCheckStockFragment : Fragment() {

    private var _binding: FragmentGodownCheckStockBinding? = null
    private val binding get() = _binding
    private lateinit var stockViewModel: StockViewModel

    private val args: GodownCheckStockFragmentArgs by navArgs()

    private lateinit var loading: ProgressBar
    private lateinit var adapter: GodownStockAdapter
    private val stocks: ArrayList<Stock> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        stockViewModel = ViewModelProvider(this).get(StockViewModel::class.java)
        _binding = FragmentGodownCheckStockBinding.inflate(layoutInflater, container, false)

        loading = _binding?.loadingBar!!
        loading.visibility = View.VISIBLE

        stockViewModel.getGodownStock(args.godownId)

        _binding?.rvGodownStockDetails?.layoutManager = LinearLayoutManager(requireContext())
        adapter = GodownStockAdapter(stocks) { id, name ->
            val action =
                GodownCheckStockFragmentDirections.actionNavGodownCheckStockToStockHistory(id, name)
            findNavController().navigate(action)
        }
        _binding?.rvGodownStockDetails?.adapter = adapter

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stockViewModel.godownStockResult.observe(viewLifecycleOwner, {
            loading.visibility = View.GONE
            if (it.error != null) {
                showError(it.error)
            }
            if (it.success != null) {
                updateUi(it.success)
            }
        })

        _binding?.ivBack?.setOnClickListener {
            findNavController().popBackStack(R.id.navigation_godown_add_stock, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showError(errorString: String) {
        Toasty.error(requireContext(), errorString).show()
    }

    private fun updateUi(list: List<Stock>) {
        stocks.clear()
        stocks.addAll(list)
        adapter.notifyDataSetChanged()
    }
}