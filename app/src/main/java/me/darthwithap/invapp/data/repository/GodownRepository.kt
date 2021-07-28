package me.darthwithap.invapp.data.repository

import me.darthwithap.api.models.requests.NewGodownRequest
import me.darthwithap.invapp.data.domain.models.Godown
import me.darthwithap.invapp.data.domain.utils.GodownDtoListMapper
import me.darthwithap.invapp.data.domain.utils.GodownDtoMapper
import me.darthwithap.invapp.data.network.GodownDataSource
import me.darthwithap.invapp.utils.Result
import java.lang.Exception

// createGodown

object GodownRepository {

    suspend fun getAllGodowns(
        refresh: Boolean
    ): Result<List<Godown>> {
        if (refresh) {
            return when (val response = GodownDataSource.getAllGodowns()) {
                is Result.Success -> {
                    if (response.data.error) {
                        Result.Error(Exception(response.data.message))
                    } else {
                        Result.Success(GodownDtoListMapper.mapToDomainModel(response.data.data))
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

    suspend fun createGodown(
        godownRequest: NewGodownRequest,
    ): Result<Godown> {
        return when (val response = GodownDataSource.createGodown(godownRequest)) {
            is Result.Success -> {
                if (response.data.error) {
                    Result.Error(Exception(response.data.message))
                } else {
                    Result.Success(GodownDtoMapper.mapToDomainModel(response.data.data))
                }
            }
            is Result.Error -> {
                Result.Error(response.exception)
            }
            else -> Result.Loading
        }
    }
}