package me.darthwithap.invapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.darthwithap.invapp.data.local.entity.GodownEntity

@Dao
interface GodownDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewGodown(godown: GodownEntity)

    @Query("SELECT * FROM godown_table")
    suspend fun getAllGodowns(): List<GodownEntity>
}