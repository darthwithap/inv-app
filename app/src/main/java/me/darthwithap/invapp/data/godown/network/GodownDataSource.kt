package me.darthwithap.invapp.data.godown.network

import me.darthwithap.api.InvApiClient
import me.darthwithap.api.models.requests.NewGodownRequest
import me.darthwithap.api.models.responses.GodownsResponse
import me.darthwithap.api.models.responses.NewGodownResponse
import me.darthwithap.invapp.utils.Result
import java.io.IOException

object GodownDataSource {
    private val authApi = InvApiClient.authApi

    suspend fun getAllGodowns(): Result<GodownsResponse> {
        return try {
            val response = authApi.getAllGodowns()
            if (response.isSuccessful) {
                response.body()?.let { Result.Success(it) }!!
            } else {
                Result.Error(IOException(response.errorBody().toString()))
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
                Result.Error(IOException(response.errorBody().toString()))
            }
        } catch (exception: java.lang.Exception) {
            Result.Error(exception)
        }
    }
}