package me.darthwithap.invapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.darthwithap.api.models.entities.dto.SalesStockDto
import me.darthwithap.invapp.data.domain.models.SalesStock

@Entity(tableName = "sales_product_table")
data class SalesProductEntity(
    @ColumnInfo(name = "delivered_status")
    val deliveredStatus: Boolean,
    @ColumnInfo(name = "godown")
    val godown: String,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "quantity")
    val quantity: Int,
    @Embedded
    @ColumnInfo(name = "stock")
    val stock: SalesStock
) {
    override fun toString(): String {
        return "$quantity $stock"
    }
}