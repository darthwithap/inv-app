package me.darthwithap.invapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock_table")
data class StockEntity(
    @ColumnInfo(name = "brand")
    val brand: String = "",
    @ColumnInfo(name = "code")
    val code: String = "",
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "godown")
    val godown: String,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "quantity")
    val quantity: Int,
    @ColumnInfo(name = "range")
    val range: String = "",
    @ColumnInfo(name = "shop")
    val shop: String,
    @ColumnInfo(name = "updated_at")
    val updatedAt: String,
)