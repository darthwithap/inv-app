package me.darthwithap.invapp.ui.orders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.darthwithap.invapp.data.models.Godown
import me.darthwithap.invapp.data.models.OrderProductItem
import me.darthwithap.invapp.databinding.ListItemGodownChipBinding
import me.darthwithap.invapp.databinding.ListItemGodownOrderProductBinding

class GodownChipAdapter(private val products: List<Godown>) :
    RecyclerView.Adapter<GodownChipAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemGodownChipBinding.inflate(
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
        fun bind(item: Godown) {
            ListItemGodownChipBinding.bind(itemView).apply {
                with(item) {
                    tvGodownName.text = name
                }
            }
        }
    }
}