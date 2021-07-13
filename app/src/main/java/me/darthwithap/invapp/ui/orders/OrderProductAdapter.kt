package me.darthwithap.invapp.ui.orders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.darthwithap.invapp.data.domain.models.OrderProductItem
import me.darthwithap.invapp.databinding.ListItemGodownOrderProductBinding

class OrderProductAdapter(private val products: List<OrderProductItem>) :
    RecyclerView.Adapter<OrderProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemGodownOrderProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: OrderProductItem) {
            ListItemGodownOrderProductBinding.bind(itemView).apply {
                with(item) {
                    tvProductName.text = name
                    tvProductBrand.text = brand ?: ""
                    tvProductCode.text = code
                    tvQuantity.text = qty.toString()
                    checkBox.isChecked = selected
                    tvProductRange.text = "%.2f".format(price)
                }
            }
        }
    }
}