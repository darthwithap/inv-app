package me.darthwithap.invapp.ui.addstock.godownstock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.darthwithap.invapp.data.domain.models.Stock
import me.darthwithap.invapp.databinding.ListItemGodownStockBinding

class GodownStockAdapter(
    private val stocks: List<Stock>,
    private val onClick: (id: String, name: String) -> Unit
) :
    RecyclerView.Adapter<GodownStockAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemGodownStockBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stocks[position])
    }

    override fun getItemCount(): Int {
        return stocks.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(stock: Stock) {
            ListItemGodownStockBinding.bind(itemView).apply {
                with(stock) {
                    tvBrand.text = brand.takeIf { it.isNotBlank() } ?: "-"
                    tvCode.text = code.takeIf { it.isNotBlank() } ?: "-"
                    tvName.text = name
                    tvRange.text = range.takeIf { it.isNotBlank() } ?: "-"
                    tvQty.text = quantity.toString()
                    root.setOnClickListener { onClick.invoke(id, name) }
                }
            }
        }
    }
}