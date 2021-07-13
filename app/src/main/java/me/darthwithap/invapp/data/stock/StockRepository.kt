package me.darthwithap.invapp.data.stock

import me.darthwithap.api.models.entities.*
import me.darthwithap.api.models.requests.AddStockRequest
import me.darthwithap.invapp.data.stock.network.StockDataSource
import me.darthwithap.invapp.utils.Result
import java.lang.Exception

object StockRepository {

    suspend fun addStock(
        stockRequest: AddStockRequest,
        refresh: Boolean
    ): Result<StockDto> {
        if (refresh) {
            //val mapper = WeatherMapperRemote()
            return when (val response = StockDataSource.addStock(stockRequest)) {
                is Result.Success -> {
                    if (response.data.error) {
                        Result.Error(Exception(response.data.message))
                    } else {
                        // TODO implement Mapper to Domain
                        //Result.Success(mapper.transformToDomain(response.data))
                        Result.Success(response.data.data)
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

    suspend fun getAllStock(
        refresh: Boolean
    ): Result<StockData> {
        if (refresh) {
            //val mapper = WeatherMapperRemote()
            return when (val response = StockDataSource.getAllStock()) {
                is Result.Success -> {
                    if (response.data.error) {
                        Result.Error(Exception(response.data.message))
                    } else {
                        // TODO implement Mapper to Domain
                        //Result.Success(mapper.transformToDomain(response.data))
                        Result.Success(response.data.data)
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
    ): Result<StockData> {
        if (refresh) {
            //val mapper = WeatherMapperRemote()
            return when (val response = StockDataSource.getGodownStock(godownId)) {
                is Result.Success -> {
                    if (response.data.error) {
                        Result.Error(Exception(response.data.message))
                    } else {
                        // TODO implement Mapper to Domain
                        //Result.Success(mapper.transformToDomain(response.data))
                        Result.Success(response.data.data)
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
    ): Result<StockHistoryData> {
        if (refresh) {
            //val mapper = WeatherMapperRemote()
            return when (val response = StockDataSource.getStockHistory(stockId)) {
                is Result.Success -> {
                    if (response.data.error) {
                        Result.Error(Exception(response.data.message))
                    } else {
                        // TODO implement Mapper to Domain
                        //Result.Success(mapper.transformToDomain(response.data))
                        Result.Success(response.data.data)
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