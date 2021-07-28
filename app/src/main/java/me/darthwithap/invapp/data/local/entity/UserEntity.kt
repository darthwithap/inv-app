package me.darthwithap.invapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "user_table")
data class UserEntity(
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "display_name")
    val displayName: String,
    @ColumnInfo(name = "is_activated")
    val isActivated: Boolean,
    @ColumnInfo(name = "role")
    val role: String,
    @ColumnInfo(name = "shop")
    val shop: String,
    @ColumnInfo(name = "updated_at")
    val updatedAt: String,
    @ColumnInfo(name = "username")
    val username: String
)