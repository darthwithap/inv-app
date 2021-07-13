package me.darthwithap.api.models.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageDto(
    @Json(name = "number")
    val number: Int,
    @Json(name = "url")
    val url: String
)