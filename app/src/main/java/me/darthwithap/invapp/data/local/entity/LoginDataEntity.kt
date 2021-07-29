package me.darthwithap.invapp.data.local.entity

import androidx.room.*
import me.darthwithap.invapp.data.domain.models.User

@Entity(tableName = "login_data_table")
data class LoginDataEntity(@PrimaryKey(autoGenerate = true) val id: Int)
//    (
//    @PrimaryKey(autoGenerate = false)
//    @ColumnInfo(name = "token")
//    var token: String,
//    @Embedded(prefix = "user")
//    var user: User
//)