package me.darthwithap.invapp.ui.addstock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.darthwithap.invapp.data.domain.models.Godown
import me.darthwithap.invapp.databinding.ListItemGodownBinding

class GodownAdapter(
    private val godowns: List<Godown>,
    val onClick: (id: String, name: String) -> Unit
) :
    RecyclerView.Adapter<GodownAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemGodownBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(godowns[position])
    }

    override fun getItemCount(): Int {
        return godowns.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Godown) {
            ListItemGodownBinding.bind(itemView).apply {
                with(item) {
                    tvGodownName.text = name
                    root.setOnClickListener { onClick.invoke(godownId, name) }
                }
            }
        }
    }
}