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
import me.darthwithap.invapp.data.domain.models.StockHistory
import me.darthwithap.invapp.databinding.FragmentGodownCheckStockBinding
import me.darthwithap.invapp.databinding.FragmentGodownStockHistoryBinding
import me.darthwithap.invapp.ui.viewmodel.StockViewModel
import okhttp3.internal.notifyAll

class GodownStockHistoryFragment : Fragment() {

    private var _binding: FragmentGodownStockHistoryBinding? = null
    private val binding get() = _binding
    private lateinit var stockViewModel: StockViewModel

    private val args: GodownStockHistoryFragmentArgs by navArgs()

    private lateinit var loading: ProgressBar
    private lateinit var adapter: GodownStockHistoryAdapter
    private val histories: ArrayList<StockHistory> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        stockViewModel = ViewModelProvider(this).get(StockViewModel::class.java)
        _binding = FragmentGodownStockHistoryBinding.inflate(layoutInflater, container, false)

        loading = _binding?.loadingBar!!
        loading.visibility = View.VISIBLE

        stockViewModel.getStockHistory(args.stockId)

        _binding?.tvStockItemName?.text = args.stockName

        _binding?.rvGodownStockHistory?.layoutManager = LinearLayoutManager(requireContext())
        adapter = GodownStockHistoryAdapter(histories)
        _binding?.rvGodownStockHistory?.adapter = adapter

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stockViewModel.stockHistoryResult.observe(viewLifecycleOwner, {
            loading.visibility = View.GONE
            if (it.error != null) {
                showError(it.error)
            }
            if (it.success != null) {
                updateUi(it.success)
            }
        })

        _binding?.ivBack?.setOnClickListener {
            findNavController().popBackStack(R.id.navigation_godown_check_stock, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showError(errorString: String) {
        Toasty.error(requireContext(), errorString).show()
    }

    private fun updateUi(list: List<StockHistory>) {
        histories.clear()
        histories.addAll(list)
        adapter.notifyDataSetChanged()
    }
}