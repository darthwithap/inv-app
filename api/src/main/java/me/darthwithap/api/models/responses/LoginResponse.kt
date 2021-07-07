package me.darthwithap.api.models.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.darthwithap.api.models.entities.LoginData

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "data")
    var data: LoginData = LoginData(),
    @Json(name = "error")
    val error: Boolean,
    @Json(name = "message")
    val message: String,
    @Json(name = "status")
    val status: Int
)