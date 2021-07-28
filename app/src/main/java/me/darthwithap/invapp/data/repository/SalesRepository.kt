package me.darthwithap.invapp.data.repository

import me.darthwithap.invapp.data.domain.models.SalesInvoice
import me.darthwithap.invapp.data.domain.utils.SalesInvoiceDtoListMapper
import me.darthwithap.invapp.data.network.SalesDataSource
import me.darthwithap.invapp.utils.Result
import java.lang.Exception

object SalesRepository {

    suspend fun getSales(
        refresh: Boolean
    ): Result<List<SalesInvoice>> {
        if (refresh) {
            return when (val response =
                SalesDataSource.getSales()
            ) {
                is Result.Success -> {
                    if (response.data.error) {
                        Result.Error(Exception(response.data.message))
                    } else {
                        Result.Success(SalesInvoiceDtoListMapper.mapToDomainModel(response.data.data.invoices))
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