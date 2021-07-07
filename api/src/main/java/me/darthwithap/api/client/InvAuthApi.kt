package me.darthwithap.api.client

import me.darthwithap.api.models.responses.LogoutResponse
import retrofit2.Response
import retrofit2.http.POST

interface InvAuthApi {
    @POST("logout")
    suspend fun logout(): Response<LogoutResponse>
}