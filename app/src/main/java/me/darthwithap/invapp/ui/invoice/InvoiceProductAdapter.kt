package me.darthwithap.invapp.ui.invoice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.darthwithap.invapp.data.models.InvoiceEntryItem
import me.darthwithap.invapp.databinding.ListItemInvoiceProductBinding

private const val TAG = "InvoiceProductAdapter"

class InvoiceProductAdapter(val editorActionListener: (Int) -> Boolean) :
    ListAdapter<InvoiceEntryItem, InvoiceProductAdapter.InvoiceViewHolder>(
        object : DiffUtil.ItemCallback<InvoiceEntryItem>() {
            override fun areItemsTheSame(
                oldItem: InvoiceEntryItem,
                newItem: InvoiceEntryItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: InvoiceEntryItem,
                newItem: InvoiceEntryItem
            ): Boolean {
                return oldItem.toString() == newItem.toString()
            }

        }
    ) {

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
        fun bind(item: InvoiceEntryItem) {
            ListItemInvoiceProductBinding.bind(itemView).apply {
                with(item) {
                    etName.setText(name)
                    etBrand.setText(brand)
                    etPrice.setText(price)
                    etQty.setText(qty.toString())
                }
                etQty.setOnEditorActionListener { _, actionId, _ ->
                    editorActionListener(actionId)
                }
            }
        }
    }
}