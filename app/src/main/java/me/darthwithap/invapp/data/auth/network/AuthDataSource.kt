package me.darthwithap.invapp.data.auth.network

import android.util.Log
import me.darthwithap.api.InvApiClient
import me.darthwithap.invapp.utils.Result
import me.darthwithap.api.models.requests.LoginRequest
import me.darthwithap.api.models.responses.LoginResponse
import org.json.JSONObject
import java.io.IOException

private const val TAG = "AuthDataSource"

object AuthDataSource {
    private val api = InvApiClient.api

    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
        return try {
            val response = api.login(loginRequest)
            if (response.isSuccessful) {
                Log.d(TAG, "login: ${response.body().toString()}")
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