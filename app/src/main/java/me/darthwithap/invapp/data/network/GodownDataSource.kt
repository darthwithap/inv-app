package me.darthwithap.invapp.data.network

import me.darthwithap.api.InvApiClient
import me.darthwithap.api.models.requests.NewGodownRequest
import me.darthwithap.api.models.responses.GodownsResponse
import me.darthwithap.api.models.responses.NewGodownResponse
import me.darthwithap.invapp.utils.Result
import org.json.JSONObject
import java.io.IOException

object GodownDataSource {
    private val authApi = InvApiClient.authApi

    suspend fun getAllGodowns(): Result<GodownsResponse> {
        return try {
            val response = authApi.getAllGodowns()
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

    suspend fun createGodown(godownRequest: NewGodownRequest): Result<NewGodownResponse> {
        return try {
            val response = authApi.createGodown(godownRequest)
            if (response.isSuccessful) {
                response.body()?.let { Result.Success(it) }!!
            } else {
                val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                Result.Error(IOException(jsonObj.getString("message")))
            }
        } catch (exception: java.lang.Exception) {
            Result.Error(exception)
        }
    }
}