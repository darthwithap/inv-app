package me.darthwithap.api.models.entities.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.darthwithap.api.models.entities.dto.UserDto

@JsonClass(generateAdapter = true)
data class LoginData(
    @Json(name = "token")
    var token: String? = null,
    @Json(name = "user")
    var user: UserDto? = null
)