package me.darthwithap.invapp.ui.invoice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import me.darthwithap.invapp.databinding.FragmentInvoiceBinding
import me.darthwithap.invapp.ui.viewmodel.InvoiceViewModel


private const val TAG = "InvoiceFragment"

class InvoiceFragment : Fragment() {

    private lateinit var invoiceViewModel: InvoiceViewModel
    private var _binding: FragmentInvoiceBinding? = null
    private lateinit var invoiceProductAdapter: InvoiceProductAdapter

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        invoiceViewModel =
            ViewModelProvider(this).get(InvoiceViewModel::class.java)
        //invoiceViewModel.addItemToProductList()
        invoiceProductAdapter = InvoiceProductAdapter {
            when (it) {
                EditorInfo.IME_ACTION_NEXT -> {
                    //invoiceViewModel.addItemToProductList()
                    // Todo shift focus to new row first edit text
                    // If edittext.isShown && !edittext.isFocused
                }
            }
            false
        }
        _binding = FragmentInvoiceBinding.inflate(inflater, container, false)

        _binding?.rvInvoiceProductItems?.layoutManager = LinearLayoutManager(context)
        _binding?.rvInvoiceProductItems?.adapter = invoiceProductAdapter

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        invoiceViewModel.invoiceProducts.observe(viewLifecycleOwner, {
//            invoiceProductAdapter.submitList(it)
//    })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}