package me.darthwithap.invapp.ui.invoice

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.darthwithap.api.models.entities.data.ProductData
import me.darthwithap.invapp.data.domain.models.StockItem
import me.darthwithap.invapp.databinding.ListItemInvoiceProductBinding
import me.darthwithap.invapp.utils.extensions.afterTextChanged

private const val TAG = "InvoiceProductAdapter"

class InvoiceProductAdapter(
    private val autocompleteAdapter: SearchAutocompleteAdapter,
    private val setStockId: (Int, String) -> Unit,
    private val setQty: (Int, String) -> Unit,
    private val searchStock: (String) -> Unit,
    private val actvEditorActionListener: (Int, Int, String) -> Boolean,
    private val qtyEditorActionListener: (Int, Int, String) -> Boolean
) :
    ListAdapter<ProductData, InvoiceProductAdapter.InvoiceViewHolder>(
        object : DiffUtil.ItemCallback<ProductData>() {
            override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
                return oldItem.toString() == newItem.toString()
            }
        }
    ) {

    private var stockId: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceViewHolder {
        val binding = ListItemInvoiceProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return InvoiceViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: InvoiceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class InvoiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ProductData) {
            ListItemInvoiceProductBinding.bind(itemView).apply {
                actvQuery.let {
                    if (it.isShown && !it.isFocused) {
                        it.requestFocus()
                    }
                }
                with(item) {
                    if (quantity != 0) etQty.setText(quantity.toString())
                }
                etQty.afterTextChanged {
                    setQty.invoke(adapterPosition, it)
                }
                actvQuery.afterTextChanged {
                    searchStock.invoke(it)
                    actvQuery.setAdapter(autocompleteAdapter)
                }
                actvQuery.setOnEditorActionListener { _, actionId, _ ->
                    actvEditorActionListener.invoke(actionId, adapterPosition, stockId)
                }
                etQty.setOnEditorActionListener { _, actionId, _ ->
                    qtyEditorActionListener(actionId, adapterPosition, etQty.text.toString())
                }
                actvQuery.setOnItemClickListener { _, _, position, _ ->
                    val stockItem: StockItem = actvQuery.adapter.getItem(position) as StockItem
                    stockId = stockItem.id
                    setStockId.invoke(adapterPosition, stockId)
                }
            }
        }
    }
}