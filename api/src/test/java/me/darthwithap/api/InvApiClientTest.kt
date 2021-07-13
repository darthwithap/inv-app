package me.darthwithap.api

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import me.darthwithap.api.models.requests.AddStockRequest
import me.darthwithap.api.models.requests.LoginRequest
import me.darthwithap.api.models.requests.NewGodownRequest
import org.junit.Test
import kotlin.random.Random

class InvApiClientTest {
    private val api = InvApiClient.api
    private val authApi = InvApiClient.authApi

    private val token: String =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MGU0OTI1MTFkOTEwOTM5ZGE0NTgyNTMiLCJpYXQiOjE2MjU5OTkyNTl9.P3IdBoJ2GZl3tk1JD6bZRKWbmTfThjFHCO365Nt6v1Q"

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
        InvApiClient.setAuthToken(token)
        runBlocking {
            val response = authApi.logout()
            assertEquals("user logged out successfully !!", response.body()?.message)
        }
    }

    @Test
    fun `POST godown`() {
        InvApiClient.setAuthToken(token)
        runBlocking {
            val response =
                authApi.createGodown(NewGodownRequest("test godown ${Random.nextInt(1, 20)}"))
            assertEquals("Godown Successfully Created", response.body()?.message)
        }
    }

    @Test
    fun `GET all godowns`() {
        InvApiClient.setAuthToken(token)
        runBlocking {
            val response =
                authApi.getAllGodowns()
            assertEquals("Godowns fetched successfully.", response.body()?.message)
        }
    }

    @Test
    fun `POST stock`() {
        InvApiClient.setAuthToken(token)
        runBlocking {
            val response =
                authApi.addStock(
                    AddStockRequest(
                        name = "Product name ${Random.nextInt()}",
                        godown = "60eaca84c37201ca3664c696",
                        quantity = 15
                    )
                )
            assertEquals("Stock create successfully.", response.body()?.message)
        }
    }

    @Test
    fun `GET all stock`() {
        InvApiClient.setAuthToken(token)
        runBlocking {
            val response =
                authApi.getAllStock()
            assertEquals("Stock fetched successfully.", response.body()?.message)
        }
    }

    @Test
    fun `GET godown stock`() {
        InvApiClient.setAuthToken(token)
        runBlocking {
            val response =
                authApi.getAllStock("60eaca84c37201ca3664c696")
            assertEquals("Stock fetched successfully.", response.body()?.message)
        }
    }

    @Test
    fun `GET stock history`() {
        InvApiClient.setAuthToken(token)
        runBlocking {
            val response =
                authApi.getStockHistory("60eab2bdc37201ca3664c67d")
            assertEquals("Stock's History fetched successfully.", response.body()?.message)
        }
    }
}