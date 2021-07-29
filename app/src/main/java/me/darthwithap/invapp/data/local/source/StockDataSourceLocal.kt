package me.darthwithap.invapp.data.local.source

import android.content.Context
import me.darthwithap.invapp.data.local.InvDatabase

class StockDataSourceLocal(val context: Context) {

    private val db = InvDatabase.getDatabase(context)
    private val stockDao = db.stockDao()

//    suspend fun insertStock(stock: StockEntity) {
//        stockDao.insertStock(stock)
//    }
//
//    suspend fun getAllStock(): List<StockEntity> {
//        return stockDao.getAllStock()
//    }
//
//    suspend fun getAllStockForGodown(godownId: String): List<StockEntity> {
//        return stockDao.getAllStockForGodown(godownId)
//    }
//
//    suspend fun insertStockHistory(stockHistory: StockHistoryEntity) {
//        return stockDao.insertStockHistory(stockHistory)
//    }
//
//    suspend fun getStockHistory(stockId: String): List<StockHistoryEntity> {
//        return stockDao.getStockHistory(stockId)
//    }
//
//    suspend fun insertSearchStockItem(stockItem: StockItemEntity) {
//        return stockDao.insertSearchStockItem(stockItem)
//    }
//
//    suspend fun getSearchStockItem(query: String): List<StockItemEntity> {
//        return stockDao.getSearchStockItem(query)
//    }
}