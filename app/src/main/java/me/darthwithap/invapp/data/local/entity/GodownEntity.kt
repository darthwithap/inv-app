package me.darthwithap.invapp.data.local.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "godown_table")
data class GodownEntity(@PrimaryKey(autoGenerate = true) val id: Int)
//    @PrimaryKey(autoGenerate = false)
//    @ColumnInfo(name = "id")
//    val id: String,
//    @ColumnInfo(name = "name")
//    val name: String
