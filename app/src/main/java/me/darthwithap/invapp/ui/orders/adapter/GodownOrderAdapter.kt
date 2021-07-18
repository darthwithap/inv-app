package me.darthwithap.invapp.ui.orders.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.darthwithap.invapp.data.domain.models.Invoice
import me.darthwithap.invapp.databinding.ListItemGodownOrderBinding

class GodownOrderAdapter(private val orders: List<Invoice>) :
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
            ListItemGodownOrderBinding.bind(itemView).apply {
                with(item) {
                    tvCustomerName.text = customerName
                    tvUsername.text = userName
                    // Order Products RecyclerView Setup
                    val productAdapter = products?.let { OrderProductAdapter(it) }
                    rvGodownOrderProducts.layoutManager = LinearLayoutManager(context)
                    rvGodownOrderProducts.adapter = productAdapter
                }
            }
        }
    }
}