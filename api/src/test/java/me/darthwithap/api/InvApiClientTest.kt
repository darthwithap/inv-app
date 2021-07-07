package me.darthwithap.api

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import me.darthwithap.api.models.requests.LoginRequest
import org.junit.Test

class InvApiClientTest {
    private val api = InvApiClient.api
    private val authApi = InvApiClient.authApi

    @Test
    fun `POST login`() {
        val loginRequest = LoginRequest("test", "test123", "000")
        runBlocking {
            val response = api.login(loginRequest)
            assertEquals("test", response.body()?.data?.user?.username)
        }
    }

    @Test
    fun `POST logout`() {
        InvApiClient.setAuthToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MGU0OTI1MTFkOTEwOTM5ZGE0NTgyNTMiLCJpYXQiOjE2MjU2OTAzNzZ9.jvhnqoCXWExeoxSvCf6kHTeoe4c0C4ImvD7tTmOB4Eg")
        runBlocking {
            val response = authApi.logout()
            assertEquals("user logged out successfully !!", response.body()?.message)
        }
    }
}