package me.darthwithap.invapp.data.auth

import me.darthwithap.api.InvApiClient
import me.darthwithap.invapp.utils.Result
import me.darthwithap.api.models.requests.LoginRequest
import me.darthwithap.api.models.responses.LoginResponse
import java.io.IOException

object AuthDataSource {
    private val api = InvApiClient.api

    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
        return try {
            val response = api.login(loginRequest)
            if (response.isSuccessful) {
                response.body()?.let { Result.Success(it) }!!
            } else {
                Result.Error(IOException("Error logging in"))
            }
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }
}