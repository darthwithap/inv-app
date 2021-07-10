package me.darthwithap.invapp.ui.addstock

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import me.darthwithap.invapp.R
import me.darthwithap.invapp.data.models.Godown
import me.darthwithap.invapp.databinding.FragmentAddStockBinding
import me.darthwithap.invapp.ui.GodownViewModel

class AddStockFragment : Fragment() {

    private lateinit var godownViewModel: GodownViewModel
    private var _binding: FragmentAddStockBinding? = null
    private val binding get() = _binding

    private lateinit var godownAdapter: GodownAdapter
    private val godowns: ArrayList<Godown> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        godownViewModel =
            ViewModelProvider(this).get(GodownViewModel::class.java)
        godownViewModel.getGodowns()
        _binding = FragmentAddStockBinding.inflate(inflater, container, false)

        _binding?.rvGodownList?.layoutManager = LinearLayoutManager(context)
        godownAdapter = GodownAdapter(godowns)
        _binding?.rvGodownList?.adapter = godownAdapter

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        godownViewModel.godowns.observe(viewLifecycleOwner, {
            godowns.clear()
            godowns.addAll(it)
            godownAdapter.notifyDataSetChanged()
        })
        binding?.btnAddGodown?.setOnClickListener {
            val dialog = context?.let { context -> Dialog(context, R.style.DialogLight) }
            dialog!!.window!!.attributes.windowAnimations = R.style.PauseDialogAnimation
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.item_dialog_input_add_godown)
            dialog.setCancelable(false)

            val size = Point()
            val wm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            display.getSize(size)
            val mWidth = size.x

            val window = dialog.window
            val wlp = window!!.attributes as WindowManager.LayoutParams

            wlp.gravity = Gravity.CENTER
            wlp.x = 0
            wlp.y = 0
            wlp.width = mWidth
            window.attributes = wlp
            dialog.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}