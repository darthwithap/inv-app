package me.darthwithap.invapp.data.network

import android.util.Log
import me.darthwithap.api.InvApiClient
import me.darthwithap.api.models.requests.CreateInvoiceRequest
import me.darthwithap.api.models.requests.PendingOrdersUpdateRequest
import me.darthwithap.api.models.responses.CreateInvoiceResponse
import me.darthwithap.api.models.responses.PendingOrdersUpdateResponse
import me.darthwithap.api.models.responses.PendingOrdersResponse
import me.darthwithap.invapp.utils.Result
import org.json.JSONObject
import java.io.IOException

private const val TAG = "TAG"

object InvoiceDataSource {
    private val authApi = InvApiClient.authApi

    suspend fun createInvoice(createInvoiceRequest: CreateInvoiceRequest): Result<CreateInvoiceResponse> {
        return try {
            val response = authApi.createInvoice(createInvoiceRequest)
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


    suspend fun getPendingOrdersForGodown(godownId: String): Result<PendingOrdersResponse> {
        return try {
            val response = authApi.getPendingOrdersForGodown(godownId)
            Log.d(TAG, "getPendingOrdersForGodown: reponse: ${response.body()?.data?.invoices}")
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

    suspend fun updatePendingOrdersStatusFor(pendingOrdersUpdateRequest: PendingOrdersUpdateRequest):
            Result<PendingOrdersUpdateResponse> {

        return try {
            val response = authApi.updatePendingOrdersStatusFor(pendingOrdersUpdateRequest)
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