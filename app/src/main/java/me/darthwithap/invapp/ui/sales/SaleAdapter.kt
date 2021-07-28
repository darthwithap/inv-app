package me.darthwithap.invapp.ui.sales

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.darthwithap.api.models.entities.dto.SalesInvoiceDto
import me.darthwithap.invapp.data.domain.models.SalesInvoice
import me.darthwithap.invapp.databinding.ListItemSaleBinding
import me.darthwithap.invapp.utils.extensions.simpleTimestamp

private const val TAG = "SaleAdapter"

class SaleAdapter(private val sales: List<SalesInvoice>) :
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
        fun bind(item: SalesInvoice) {
            ListItemSaleBinding.bind(itemView).apply {
                with(item) {
                    tvDot.visibility =
                        if (products.any { !it.deliveredStatus }) View.VISIBLE else View.INVISIBLE
                    tvCustomerName.text = customerName
                    tvSalesPersonName.text = user
                    tvDate.simpleTimestamp = updatedAt
                    tvSaleDetails.text = products.joinToString(separator = ", ")
                }
            }
        }
    }
}