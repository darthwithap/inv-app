package me.darthwithap.invapp.data.sales.network

import me.darthwithap.api.InvApiClient
import me.darthwithap.api.models.requests.CreateInvoiceRequest
import me.darthwithap.api.models.requests.PendingOrdersUpdateRequest
import me.darthwithap.api.models.responses.CreateInvoiceResponse
import me.darthwithap.api.models.responses.PendingOrdersUpdateResponse
import me.darthwithap.api.models.responses.PendingOrdersResponse
import me.darthwithap.api.models.responses.SalesResponse
import me.darthwithap.invapp.utils.Result
import org.json.JSONObject
import java.io.IOException

object SalesDataSource {
    private val authApi = InvApiClient.authApi

    suspend fun getSales(): Result<SalesResponse> {
        return try {
            val response = authApi.getSales()
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