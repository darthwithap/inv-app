package me.darthwithap.invapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.darthwithap.invapp.data.domain.models.StockHistoryUser

@Entity(tableName = "stock_history_table")
data class StockHistoryEntity(
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "quantity")
    val quantity: Int,
    @ColumnInfo(name = "stock")
    val stock: String,
    @ColumnInfo(name = "updated_at")
    val updatedAt: String,
    @Embedded
    @ColumnInfo(name = "user")
    val user: StockHistoryUser,
)