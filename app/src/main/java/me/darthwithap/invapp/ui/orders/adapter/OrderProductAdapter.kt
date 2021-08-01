package me.darthwithap.invapp.ui.orders.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import me.darthwithap.invapp.data.domain.models.Product
import me.darthwithap.invapp.databinding.ListItemGodownOrderProductBinding

private const val TAG = "TAG"
class OrderProductAdapter(
    private val products: List<Product>,
    private val onCheckChanged: (buttonView: CompoundButton, isChecked: Boolean, productId: String) -> Unit
) :
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
            var productId: String
            ListItemGodownOrderProductBinding.bind(itemView).apply {
                with(item) {
                    tvProductName.text = stock.name
                    tvProductBrand.text = stock.brand
                    tvProductCode.text = id
                    productId = id
                    tvQuantity.text = qty.toString()
                    Log.d(TAG, "bind: deliveredStatus: $deliveredStatus")
                    checkBox.isChecked = deliveredStatus
                    checkBox.isEnabled = !deliveredStatus
                    tvProductRange.visibility = View.GONE
                }
                checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                    onCheckChanged.invoke(buttonView, isChecked, productId)
                }
            }
        }
    }
}