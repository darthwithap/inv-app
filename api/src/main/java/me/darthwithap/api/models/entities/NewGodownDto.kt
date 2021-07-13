package me.darthwithap.api.models.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewGodownDto(
    @Json(name = "createdAt")
    val createdAt: String? = null,
    @Json(name = "_id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "shop")
    val shop: String? = null,
    @Json(name = "updatedAt")
    val updatedAt: String? = null,
    @Json(name = "__v")
    val v: Int? = null
)