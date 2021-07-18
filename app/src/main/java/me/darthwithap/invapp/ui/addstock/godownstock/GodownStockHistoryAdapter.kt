package me.darthwithap.invapp.ui.addstock.godownstock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.darthwithap.invapp.data.domain.models.StockHistory
import me.darthwithap.invapp.databinding.ListItemGodownStockBinding
import me.darthwithap.invapp.databinding.ListItemGodownStockHistoryBinding

class GodownStockHistoryAdapter(
    private val histories: List<StockHistory>
) :
    RecyclerView.Adapter<GodownStockHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemGodownStockHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(histories[position])
    }

    override fun getItemCount(): Int {
        return histories.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(history: StockHistory) {
            ListItemGodownStockHistoryBinding.bind(itemView).apply {
                with(history) {
                    tvUser.text = user.displayName
                    tvStock.text = stock
                    tvQty.text = quantity.toString()
                }
            }
        }
    }
}