package me.darthwithap.invapp.ui.sales

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.opengl.Visibility
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import me.darthwithap.invapp.data.models.Sale
import me.darthwithap.invapp.databinding.ListItemSaleBinding

class SaleAdapter(private val sales: List<Sale>) :
    RecyclerView.Adapter<SaleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemSaleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sales[position])
    }

    override fun getItemCount(): Int {
        return sales.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("NewApi", "SimpleDateFormat")
        fun bind(item: Sale) {
            ListItemSaleBinding.bind(itemView).apply {
                with(item) {
                    val simpleDateFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        SimpleDateFormat("hh:mm:ss")
                    } else {
                        SimpleDateFormat()
                    }
                    tvDot.visibility = if (isNewSale) View.INVISIBLE else View.VISIBLE
                    tvCustomerName.text = customerName
                    tvSalesPersonName.text = sellerName
                    tvDate.text = simpleDateFormat.format(date)
                    tvSaleDetails.text = saleDetails
                }
            }
        }
    }
}