package me.darthwithap.invapp.ui.addstock.godownstock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import me.darthwithap.invapp.R
import me.darthwithap.invapp.databinding.FragmentGodownAddStockBinding

class GodownAddStockFragment : Fragment() {

    private var _binding: FragmentGodownAddStockBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGodownAddStockBinding.inflate(layoutInflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.llCheckStock?.setOnClickListener {
            //val action = GodownAddStockFragmentDirections.actionNavGodownAddStockToCheckStock()
            //findNavController().navigate(action)
            findNavController().navigate(R.id.action_nav_godown_add_stock_to_check_stock)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}