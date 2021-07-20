package me.darthwithap.invapp.ui.invoice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes

class GodownDropdownAdapter(
    context: Context,
    @LayoutRes private val resource: Int,
    private val items: List<GodownItem>
) :
    ArrayAdapter<GodownItem>(context, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent)
    }

    private fun createViewFromResource(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context)
            .inflate(resource, parent, false) as TextView
        view.text = items[position].name
        return view
    }

}