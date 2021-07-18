package me.darthwithap.invapp.ui.invoice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.darthwithap.invapp.data.domain.models.Invoice
import me.darthwithap.invapp.databinding.ListItemInvoiceProductBinding

private const val TAG = "InvoiceProductAdapter"

class InvoiceProductAdapter(val editorActionListener: (Int) -> Boolean) :
    ListAdapter<Invoice, InvoiceProductAdapter.InvoiceViewHolder>(
        object : DiffUtil.ItemCallback<Invoice>() {
            override fun areItemsTheSame(
                oldItem: Invoice,
                newItem: Invoice
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Invoice,
                newItem: Invoice
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
        fun bind(item: Invoice) {
            ListItemInvoiceProductBinding.bind(itemView).apply {
                with(item) {
//                    etName.setText(products.get())
//                    etBrand.setText(brand)
//                    etPrice.setText(price)
//                    etQty.setText(qty.toString())
                }
                etQty.setOnEditorActionListener { _, actionId, _ ->
                    editorActionListener(actionId)
                }
            }
        }
    }
}