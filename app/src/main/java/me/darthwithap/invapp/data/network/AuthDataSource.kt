package me.darthwithap.invapp.data.network

import me.darthwithap.api.InvApiClient
import me.darthwithap.invapp.utils.Result
import me.darthwithap.api.models.requests.LoginRequest
import me.darthwithap.api.models.responses.LoginResponse
import me.darthwithap.api.models.responses.LogoutResponse
import org.json.JSONObject
import java.io.IOException

private const val TAG = "AuthDataSource"

object AuthDataSource {
    private val api = InvApiClient.api
    private val authApi = InvApiClient.authApi

    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
        return try {
            val response = api.login(loginRequest)
            if (response.isSuccessful) {
                response.body()?.let { Result.Success(it) }!!
            } else {
                val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                Result.Error(IOException(jsonObj.getString("message")))
            }
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

    suspend fun logout(): Result<LogoutResponse> {
        return try {
            val response = authApi.logout()
            if (response.isSuccessful) {
                response.body()?.let { Result.Success(it) }!!
            } else {
                val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                Result.Error(IOException(jsonObj.getString("message")))
            }
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }
}