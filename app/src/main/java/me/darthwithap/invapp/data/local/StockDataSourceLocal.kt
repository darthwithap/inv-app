package me.darthwithap.invapp.data.local

import android.content.Context
import me.darthwithap.api.InvApiClient
import me.darthwithap.api.models.requests.AddStockRequest
import me.darthwithap.api.models.responses.AddStockResponse
import me.darthwithap.api.models.responses.StockHistoryResponse
import me.darthwithap.api.models.responses.StockResponse
import me.darthwithap.api.models.responses.StockSearchResponse
import me.darthwithap.invapp.data.local.entity.SalesInvoiceEntity
import me.darthwithap.invapp.data.local.entity.StockEntity
import me.darthwithap.invapp.data.local.entity.StockHistoryEntity
import me.darthwithap.invapp.data.local.entity.StockItemEntity
import me.darthwithap.invapp.utils.Result
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception

class StockDataSourceLocal(val context: Context) {

    private val db = InvDatabase.getDatabase(context)
    private val stockDao = db.stockDao()

    suspend fun insertStock(stock: StockEntity) {
        stockDao.insertStock(stock)
    }

    suspend fun getAllStock(): List<StockEntity> {
        return stockDao.getAllStock()
    }

    suspend fun getAllStockForGodown(godownId: String): List<StockEntity> {
        return stockDao.getAllStockForGodown(godownId)
    }

    suspend fun insertStockHistory(stockHistory: StockHistoryEntity) {
        return stockDao.insertStockHistory(stockHistory)
    }

    suspend fun getStockHistory(stockId: String): List<StockHistoryEntity> {
        return stockDao.getStockHistory(stockId)
    }

    suspend fun insertSearchStockItem(stockItem: StockItemEntity) {
        return stockDao.insertSearchStockItem(stockItem)
    }

    suspend fun getSearchStockItem(query: String): List<StockItemEntity> {
        return stockDao.getSearchStockItem(query)
    }
}