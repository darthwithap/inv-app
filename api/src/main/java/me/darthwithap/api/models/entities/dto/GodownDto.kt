package me.darthwithap.api.models.entities.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GodownDto(
    @Json(name = "_id")
    val id: String,
    @Json(name = "name")
    val name: String
)