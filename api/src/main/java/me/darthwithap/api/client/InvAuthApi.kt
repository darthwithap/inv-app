package me.darthwithap.api.client

import me.darthwithap.api.models.requests.AddStockRequest
import me.darthwithap.api.models.requests.NewGodownRequest
import me.darthwithap.api.models.responses.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface InvAuthApi {
    @POST("logout")
    suspend fun logout(): Response<LogoutResponse>

    @POST("godown")
    suspend fun createGodown(
        @Body newGodownRequest: NewGodownRequest
    ): Response<NewGodownResponse>

    @GET("godown")
    suspend fun getAllGodowns(): Response<GodownsResponse>

    @POST("stock")
    suspend fun addStock(
        @Body addStockRequest: AddStockRequest
    ): Response<AddStockResponse>

    @GET("stock")
    suspend fun getAllStock(
        @Query("godown") godownId: String? = null
    ): Response<StockResponse>

    @GET("stock/history")
    suspend fun getStockHistory(
        @Query("stock") stockId: String
    ): Response<StockHistoryResponse>

}