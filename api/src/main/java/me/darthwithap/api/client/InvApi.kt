package me.darthwithap.api.client

import me.darthwithap.api.models.requests.LoginRequest
import me.darthwithap.api.models.responses.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface InvApi {
    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>
}
