package me.darthwithap.api.models.entities.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "isActivated")
    val isActivated: Boolean,
    @Json(name = "role")
    val role: String,
    @Json(name = "shop")
    val shop: String,
    @Json(name = "updatedAt")
    val updatedAt: String,
    @Json(name = "userName")
    val username: String
)