package me.darthwithap.invapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.darthwithap.invapp.data.local.entity.StockEntity
import me.darthwithap.invapp.data.local.entity.StockHistoryEntity
import me.darthwithap.invapp.data.local.entity.StockItemEntity

@Dao
interface StockDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStock(stock: StockEntity)

    @Query("SELECT * FROM stock_table")
    suspend fun getAllStock(): List<StockEntity>

    @Query("SELECT * FROM stock_table WHERE godown=:godownId")
    suspend fun getAllStockForGodown(godownId: String): List<StockEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStockHistory(stockHistory: StockHistoryEntity)

    @Query("SELECT * FROM stock_history_table WHERE stock=:stockId")
    suspend fun getStockHistory(stockId: String): List<StockHistoryEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertSearchStockItem(stockItem: StockItemEntity)

    @Query("SELECT * FROM stock_item_table WHERE display_name LIKE '%' || :query || '%'")
    suspend fun getSearchStockItem(query: String): List<StockItemEntity>


}