package me.darthwithap.invapp.ui.addstock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import me.darthwithap.invapp.databinding.FragmentAddStockBinding

class AddStockFragment : Fragment() {

    private lateinit var addStockViewModel: AddStockViewModel
    private var _binding: FragmentAddStockBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addStockViewModel =
            ViewModelProvider(this).get(AddStockViewModel::class.java)

        _binding = FragmentAddStockBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textView
        addStockViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}