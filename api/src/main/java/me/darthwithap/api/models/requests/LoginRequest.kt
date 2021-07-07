package me.darthwithap.api.models.requests


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequest(
    @Json(name = "userName")
    val username: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "shop")
    val shop: String
)