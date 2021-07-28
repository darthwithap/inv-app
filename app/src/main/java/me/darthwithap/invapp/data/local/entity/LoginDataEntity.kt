package me.darthwithap.invapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity

@Entity(tableName = "login_data_table")
data class LoginDataEntity(
    @ColumnInfo(name = "token")
    var token: String? = null,
    @Embedded
    @ColumnInfo(name = "user")
    var user: UserEntity? = null
)