package me.darthwithap.invapp.data.local.entity


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.darthwithap.api.models.entities.dto.ProductDto
import me.darthwithap.invapp.data.domain.models.Product

@Entity(tableName = "invoice_table")
data class InvoiceEntity(@PrimaryKey(autoGenerate = true) val id: Int)
//    (
//    @ColumnInfo(name = "created_at")
//    val createdAt: String,
//    @ColumnInfo(name = "customer_name")
//    val customerName: String,
//    @PrimaryKey(autoGenerate = false)
//    @ColumnInfo(name = "id")
//    val id: String,
//    @Embedded(prefix = "products")
//    val products: List<Product> = listOf(),
//    @ColumnInfo(name = "shop")
//    val shop: String,
//    @ColumnInfo(name = "updated_at")
//    val updatedAt: String,
//    @ColumnInfo(name = "user")
//    val user: String,
//)