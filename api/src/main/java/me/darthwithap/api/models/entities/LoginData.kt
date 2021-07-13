package me.darthwithap.api.models.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginData(
    @Json(name = "token")
    var token: String? = null,
    @Json(name = "user")
    var user: UserDto? = null
)