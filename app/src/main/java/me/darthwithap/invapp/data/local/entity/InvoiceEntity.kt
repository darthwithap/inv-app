package me.darthwithap.invapp.data.local.entity


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.darthwithap.api.models.entities.dto.ProductDto
import me.darthwithap.invapp.data.domain.models.Product

@Entity(tableName = "invoice_table")
data class InvoiceEntity(
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "customer_name")
    val customerName: String,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @Embedded
    @ColumnInfo(name = "products")
    val products: List<ProductEntity>,
    @ColumnInfo(name = "shop")
    val shop: String,
    @ColumnInfo(name = "updated_at")
    val updatedAt: String,
    @ColumnInfo(name = "user")
    val user: String,
)