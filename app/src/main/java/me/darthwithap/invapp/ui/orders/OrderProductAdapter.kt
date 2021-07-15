package me.darthwithap.invapp.ui.orders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.darthwithap.invapp.data.domain.models.Product
import me.darthwithap.invapp.databinding.ListItemGodownOrderProductBinding

class OrderProductAdapter(private val products: List<Product>) :
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
        fun bind(item: Product) {
            ListItemGodownOrderProductBinding.bind(itemView).apply {
                with(item) {
                    tvProductName.text = godown
                    //tvProductBrand.text = brand ?: ""
                    tvProductCode.text = id
                    tvQuantity.text = qty.toString()
                    checkBox.isChecked = deliveredStatus
                    //tvProductRange.text = "%.2f".format(price)
                }
            }
        }
    }
}