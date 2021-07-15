package me.darthwithap.invapp.ui.addstock

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import es.dmoral.toasty.Toasty
import me.darthwithap.invapp.R
import me.darthwithap.invapp.databinding.FragmentAddStockBinding
import me.darthwithap.invapp.data.domain.models.Godown
import me.darthwithap.invapp.ui.viewmodel.GodownViewModel
import me.darthwithap.invapp.utils.extensions.showKeyboard

class AddStockFragment : Fragment() {

    private lateinit var godownViewModel: GodownViewModel
    private var _binding: FragmentAddStockBinding? = null
    private val binding get() = _binding

    private lateinit var godownAdapter: GodownAdapter
    private val godowns: ArrayList<Godown> = arrayListOf()

    private var dialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        godownViewModel =
            ViewModelProvider(this).get(GodownViewModel::class.java)
        godownViewModel.getAllGodowns()
        _binding = FragmentAddStockBinding.inflate(inflater, container, false)

        _binding?.rvGodownList?.layoutManager = LinearLayoutManager(context)
        godownAdapter = GodownAdapter(godowns) { id, name ->
            val intent = Intent(activity, GodownStockActivity::class.java)
            intent.apply {
                putExtra(getString(R.string.intent_extra_godown_id), id)
                putExtra(getString(R.string.intent_extra_godown_name), name)
            }
            startActivity(intent)
        }
        _binding?.rvGodownList?.adapter = godownAdapter

        godownViewModel.godownsResult.observe(viewLifecycleOwner, {
            //loading.visibility = View.GONE
            if (it.error != null) {
                showError(it.error)
            }
            if (it.success != null) {
                updateUi(it.success)
            }
        })

        godownViewModel.newGodownResult.observe(viewLifecycleOwner, {
            //loading.visibility = View.GONE
            if (it.error != null) {
                dialog?.dismiss()
                showError(it.error)
            }
            if (it.success != null) {
                dialog?.dismiss()
                newGodownUpdateUi(it.success)
            }
        })

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnAddGodown?.apply {
            setOnClickListener {
                dialog = context?.let { context -> Dialog(context, R.style.DialogLight) }
                dialog?.window?.attributes?.windowAnimations = R.style.PauseDialogAnimation
                dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog?.setContentView(R.layout.item_dialog_input_add_godown)
                dialog?.setCancelable(false)

                val size = Point()
                val wm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                val display = wm.defaultDisplay
                display.getSize(size)
                val mWidth = size.x

                val window = dialog?.window
                val wlp = window!!.attributes as WindowManager.LayoutParams

                wlp.gravity = Gravity.CENTER
                wlp.x = 0
                wlp.y = 0
                wlp.width = mWidth
                window.attributes = wlp
                dialog?.show()
                initDialog()
            }
        }
    }

    private fun initDialog() {

        val etGodownName = dialog?.findViewById<EditText>(R.id.et_godown_name)

        etGodownName?.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    if (etGodownName?.text.isNullOrBlank()) {
                        showError("Name cannot be blank")
                        dialog?.dismiss()
                    }
                    etGodownName.text.toString()
                        .takeIf {
                            it.isNotBlank()
                        }?.let {
                            godownViewModel.createGodown(
                                it
                            )
                        }
                }
            }
            false
        }
        etGodownName?.requestFocus()
        context?.showKeyboard()
    }

    private fun newGodownUpdateUi(godown: Godown) {
        showSuccess("${godown.name} created successfully")
        godowns.add(godown)
        godownAdapter.notifyDataSetChanged()
    }

    private fun showSuccess(success: String) {
        context?.let { Toasty.success(it, success).show() }
    }

    private fun updateUi(list: List<Godown>) {
        godowns.clear()
        godowns.addAll(list)
        godownAdapter.notifyDataSetChanged()
    }

    private fun showError(error: String) {
        context?.let { Toasty.error(it, error).show() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}