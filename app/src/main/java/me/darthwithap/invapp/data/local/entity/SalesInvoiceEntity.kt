package me.darthwithap.invapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity

@Entity(tableName = "sales_invoice_table")
data class SalesInvoiceEntity(
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "customer_name")
    val customerName: String,
    @ColumnInfo(name = "id")
    val id: String,
    @Embedded
    @ColumnInfo(name = "products")
    val products: List<SalesProductEntity>,
    @ColumnInfo(name = "shop")
    val shop: String,
    @ColumnInfo(name = "updated_at")
    val updatedAt: String,
    @ColumnInfo(name = "user")
    val user: String,
)