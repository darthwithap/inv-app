package me.darthwithap.invapp.data.stock.network

import me.darthwithap.api.InvApiClient
import me.darthwithap.api.models.requests.AddStockRequest
import me.darthwithap.api.models.responses.AddStockResponse
import me.darthwithap.api.models.responses.StockHistoryResponse
import me.darthwithap.api.models.responses.StockResponse
import me.darthwithap.invapp.utils.Result
import java.io.IOException
import java.lang.Exception

object StockDataSource {
    private val authApi = InvApiClient.authApi

    suspend fun addStock(stockRequest: AddStockRequest): Result<AddStockResponse> {
        return try {
            val response = authApi.addStock(stockRequest)
            if (response.isSuccessful) {
                response.body()?.let { Result.Success(it) }!!
            } else {
                Result.Error(IOException(response.errorBody().toString()))
            }
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

    suspend fun getAllStock(): Result<StockResponse> {
        return try {
            val response = authApi.getAllStock()
            if (response.isSuccessful) {
                response.body()?.let { Result.Success(it) }!!
            } else {
                Result.Error(IOException(response.errorBody().toString()))
            }
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

    suspend fun getGodownStock(godownId: String): Result<StockResponse> {
        return try {
            val response = authApi.getAllStock(godownId)
            if (response.isSuccessful) {
                response.body()?.let { Result.Success(it) }!!
            } else {
                Result.Error(IOException(response.errorBody().toString()))
            }
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

    suspend fun getStockHistory(stockId: String): Result<StockHistoryResponse> {
        return try {
            val response = authApi.getStockHistory(stockId)
            if (response.isSuccessful) {
                response.body()?.let { Result.Success(it) }!!
            } else {
                Result.Error(IOException(response.errorBody().toString()))
            }
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }
}