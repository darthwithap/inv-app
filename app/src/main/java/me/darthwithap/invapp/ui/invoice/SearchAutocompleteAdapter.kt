package me.darthwithap.invapp.ui.invoice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextClock
import android.widget.TextView
import androidx.annotation.LayoutRes
import me.darthwithap.invapp.R
import me.darthwithap.invapp.data.domain.models.StockItem

class SearchAutocompleteAdapter(
    context: Context,
    @LayoutRes private val resource: Int,
    private val items: List<StockItem>
) :
    ArrayAdapter<StockItem>(context, resource, items) {

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
        val view: LinearLayout = convertView as LinearLayout? ?: LayoutInflater.from(context)
            .inflate(resource, parent, false) as LinearLayout

        val name = view.findViewById<TextView>(R.id.tv_search_name)
        val qty = view.findViewById<TextView>(R.id.tv_search_qty)
        name.text = items[position].displayName
        qty.text = items[position].quantity.toString()
        return view
    }

}