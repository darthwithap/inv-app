package me.darthwithap.invapp.data.repository

import android.util.Log
import me.darthwithap.api.InvApiClient
import me.darthwithap.api.models.entities.data.LoginData
import me.darthwithap.api.models.requests.LoginRequest
import me.darthwithap.invapp.data.local.AuthDataSourceLocal
import me.darthwithap.invapp.data.network.AuthDataSource
import me.darthwithap.invapp.utils.Result
import java.lang.Exception

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

private const val TAG = "AuthRepository"

object AuthRepository {

    suspend fun logout(): Result<String> {
        return when (val response = AuthDataSource.logout()) {
            is Result.Success -> {
                if (response.data.error) {
                    Result.Error(Exception(response.data.message))
                } else {
                    Result.Success(response.data.message)
                }
            }
            is Result.Error -> {
                Result.Error(response.exception)
            }
            else -> Result.Loading
        }
    }

    suspend fun login(loginRequest: LoginRequest): Result<LoginData> {
        return when (val response = AuthDataSource.login(loginRequest)) {
            is Result.Success -> {
                if (response.data.error) {
                    Log.d(TAG, "login: error: ${response.data.error} mg: ${response.data.message}")
                    Result.Error(Exception(response.data.message))
                } else {
                    InvApiClient.setAuthToken(response.data.data.token)
                    Result.Success(response.data.data)
                }
            }
            is Result.Error -> {
                Result.Error(response.exception)
            }
            else -> Result.Loading
        }
    }
}