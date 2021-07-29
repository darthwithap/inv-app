package me.darthwithap.invapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock_item_table")
data class StockItemEntity(@PrimaryKey(autoGenerate = true) val id: Int)
//    (
//    @ColumnInfo(name = "display_name")
//    val displayName: String,
//    @ColumnInfo(name = "godown")
//    val godown: String,
//    @PrimaryKey(autoGenerate = false)
//    @ColumnInfo(name = "id")
//    val id: String,
//    @ColumnInfo(name = "quantity")
//    val quantity: Int
//)