package me.darthwithap.invapp.data.stock

import me.darthwithap.api.models.entities.data.StockData
import me.darthwithap.api.models.entities.data.StockHistoryData
import me.darthwithap.api.models.entities.data.StockSearchData
import me.darthwithap.api.models.entities.dto.StockDto
import me.darthwithap.api.models.requests.AddStockRequest
import me.darthwithap.api.models.responses.StockSearchResponse
import me.darthwithap.invapp.data.domain.models.Stock
import me.darthwithap.invapp.data.domain.models.StockHistory
import me.darthwithap.invapp.data.domain.models.StockItem
import me.darthwithap.invapp.data.domain.utils.*
import me.darthwithap.invapp.data.stock.network.StockDataSource
import me.darthwithap.invapp.utils.Result
import java.lang.Exception

object StockRepository {

    suspend fun addStock(
        stockRequest: AddStockRequest,
    ): Result<Stock> {
        return when (val response = StockDataSource.addStock(stockRequest)) {
            is Result.Success -> {
                if (response.data.error) {
                    Result.Error(Exception(response.data.message))
                } else {
                    Result.Success(StockDtoMapper.mapToDomainModel(response.data.data))
                }
            }
            is Result.Error -> {
                Result.Error(response.exception)
            }
            else -> Result.Loading
        }
    }

    suspend fun getAllStock(
        refresh: Boolean
    ): Result<List<Stock>> {
        if (refresh) {
            return when (val response = StockDataSource.getAllStock()) {
                is Result.Success -> {
                    if (response.data.error) {
                        Result.Error(Exception(response.data.message))
                    } else {
                        Result.Success(StockDtoListMapper.mapToDomainModel(response.data.data.stock))
                    }
                }
                is Result.Error -> {
                    Result.Error(response.exception)
                }
                else -> Result.Loading
            }
        } else {
            return Result.Error(Exception("Cache Error"))
        }
        // TODO Restore from cache first
        // FROM CACHE
//        else {
//            val mapper = WeatherMapperLocal()
//            val forecast = localDataSource.getWeather()
//            if (forecast != null) {
//                Result.Success(mapper.transformToDomain(forecast))
//            } else {
//                Result.Success()
//            }
//        }
    }

    suspend fun getGodownStock(
        godownId: String,
        refresh: Boolean
    ): Result<List<Stock>> {
        if (refresh) {
            return when (val response = StockDataSource.getGodownStock(godownId)) {
                is Result.Success -> {
                    if (response.data.error) {
                        Result.Error(Exception(response.data.message))
                    } else {
                        Result.Success(StockDtoListMapper.mapToDomainModel(response.data.data.stock))
                    }
                }
                is Result.Error -> {
                    Result.Error(response.exception)
                }
                else -> Result.Loading
            }
        } else {
            return Result.Error(Exception("Cache Error"))
        }
        // TODO Restore from cache first
        // FROM CACHE
//        else {
//            val mapper = WeatherMapperLocal()
//            val forecast = localDataSource.getWeather()
//            if (forecast != null) {
//                Result.Success(mapper.transformToDomain(forecast))
//            } else {
//                Result.Success()
//            }
//        }
    }

    suspend fun getStockHistory(
        stockId: String,
        refresh: Boolean
    ): Result<List<StockHistory>> {
        if (refresh) {
            return when (val response = StockDataSource.getStockHistory(stockId)) {
                is Result.Success -> {
                    if (response.data.error) {
                        Result.Error(Exception(response.data.message))
                    } else {
                        Result.Success(StockHistoryDtoListMapper.mapToDomainModel(response.data.data.stockHistory))
                    }
                }
                is Result.Error -> {
                    Result.Error(response.exception)
                }
                else -> Result.Loading
            }
        } else {
            return Result.Error(Exception("Cache Error"))
        }
        // TODO Restore from cache first
        // FROM CACHE
//        else {
//            val mapper = WeatherMapperLocal()
//            val forecast = localDataSource.getWeather()
//            if (forecast != null) {
//                Result.Success(mapper.transformToDomain(forecast))
//            } else {
//                Result.Success()
//            }
//        }
    }

    suspend fun searchStock(
        query: String,
        refresh: Boolean
    ): Result<List<StockItem>> {
        if (refresh) {
            return when (val response = StockDataSource.searchStock(query)) {
                is Result.Success -> {
                    if (response.data.error) {
                        Result.Error(Exception(response.data.message))
                    } else {
                        Result.Success(StockItemDtoListMapper.mapToDomainModel(response.data.data.stockItems))
                    }
                }
                is Result.Error -> {
                    Result.Error(response.exception)
                }
                else -> Result.Loading
            }
        } else {
            return Result.Error(Exception("Cache Error"))
        }
        // TODO Restore from cache first
        // FROM CACHE
//        else {
//            val mapper = WeatherMapperLocal()
//            val forecast = localDataSource.getWeather()
//            if (forecast != null) {
//                Result.Success(mapper.transformToDomain(forecast))
//            } else {
//                Result.Success()
//            }
//        }
    }

}