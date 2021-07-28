package me.darthwithap.invapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.darthwithap.invapp.data.domain.models.PendingStock

@Entity(tableName = "product_table")
data class ProductEntity(
    @ColumnInfo(name = "invoice_id")
    val invoiceId: String,
    @ColumnInfo(name = "delivered_status")
    val deliveredStatus: Boolean,
    @ColumnInfo(name = "godown")
    val godown: String,
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo(name = "quantity")
    val quantity: Int,
    @Embedded
    @ColumnInfo(name = "stock")
    val stock: PendingStock
)