package me.darthwithap.invapp.ui.orders.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.darthwithap.invapp.data.domain.models.Invoice
import me.darthwithap.invapp.databinding.ListItemGodownOrderBinding

private const val TAG = "GodownOrderAdapter"
class GodownOrderAdapter(
    private val orders: List<Invoice>,
    private val onCheckedChange: (
        buttonView: CompoundButton,
        isChecked: Boolean,
        productId: String,
        invoiceId: String
    ) -> Unit
) :
    RecyclerView.Adapter<GodownOrderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemGodownOrderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(orders[position], holder.itemView.context)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Invoice, context: Context) {
            var invoiceId: String
            ListItemGodownOrderBinding.bind(itemView).apply {
                with(item) {
                    tvCustomerName.text = customerName
                    tvUsername.text = userName
                    invoiceId = id
                    val productAdapter = products?.let {
                        it.forEach{
                            Log.d(TAG, "bind: deliveredStatus: ${it.deliveredStatus}")
                        }
                        OrderProductAdapter(it) { buttonView, isChecked, productId ->
                            onCheckedChange.invoke(buttonView, isChecked, productId, invoiceId)
                        }
                    }
                    rvGodownOrderProducts.layoutManager = LinearLayoutManager(context)
                    rvGodownOrderProducts.adapter = productAdapter
                }
            }
        }
    }
}