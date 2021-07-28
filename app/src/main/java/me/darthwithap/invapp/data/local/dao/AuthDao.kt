package me.darthwithap.invapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.darthwithap.invapp.data.local.entity.LoginDataEntity
import me.darthwithap.invapp.data.local.entity.UserEntity

@Dao
interface AuthDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(loginData: LoginDataEntity)

    @Query("SELECT * FROM login_data_table")
    suspend fun getLoginData(): LoginDataEntity

    @Query("SELECT * FROM user_table ORDER BY created_at DESC LIMIT 1")
    suspend fun getCurrentUser(): UserEntity
}