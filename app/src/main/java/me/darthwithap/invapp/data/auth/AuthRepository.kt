package me.darthwithap.invapp.data.auth

import me.darthwithap.api.InvApiClient
import me.darthwithap.api.models.entities.LoginData
import me.darthwithap.api.models.entities.User
import me.darthwithap.api.models.requests.LoginRequest
import me.darthwithap.invapp.utils.Result
import java.lang.Exception

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

object AuthRepository {

    fun logout() {
        //TODO implement Logout in AuthRepo
        //LoginDataSource.logout()
    }

    suspend fun login(loginRequest: LoginRequest, refresh: Boolean): Result<LoginData> {
        if (refresh) {
            //val mapper = WeatherMapperRemote()
            return when (val response = AuthDataSource.login(loginRequest)) {
                is Result.Success -> {
                    if (response.data.error) {
                        Result.Error(Exception(response.data.message))
                    } else {
                        // TODO implement Mapper to Domain
                        //Result.Success(mapper.transformToDomain(response.data))
                        InvApiClient.setAuthToken(response.data.data.token)
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