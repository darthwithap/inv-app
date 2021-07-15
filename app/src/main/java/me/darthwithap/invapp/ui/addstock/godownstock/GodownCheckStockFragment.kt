package me.darthwithap.invapp.ui.addstock.godownstock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import me.darthwithap.invapp.databinding.FragmentGodownAddStockBinding
import me.darthwithap.invapp.databinding.FragmentGodownCheckStockBinding

class GodownCheckStockFragment : Fragment() {

    private var _binding: FragmentGodownCheckStockBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGodownCheckStockBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}