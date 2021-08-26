package me.darthwithap.invapp.ui.orders.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.darthwithap.invapp.R
import me.darthwithap.invapp.data.domain.models.Godown
import me.darthwithap.invapp.databinding.ListItemGodownChipBinding

class GodownChipAdapter(
  private val godowns: List<Godown>,
  private val onClick: (id: String, name: String) -> Unit
) :
  RecyclerView.Adapter<GodownChipAdapter.ViewHolder>() {

  private var currGodown: String = ""

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding = ListItemGodownChipBinding.inflate(
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
      ListItemGodownChipBinding.bind(itemView).apply {
        with(item) {
          if (currGodown == godownId) root.setChipBackgroundColorResource(R.color.colorPrimary)
          else root.setChipBackgroundColorResource(R.color.colorPrimary20)
          tvGodownName.text = name
          root.setOnClickListener { onClick.invoke(godownId, name) }
        }
      }
    }
  }

  fun setCurrentGodown(godownId: String) {
    currGodown = godownId
  }
}
