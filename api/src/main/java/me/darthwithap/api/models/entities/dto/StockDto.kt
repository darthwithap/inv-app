package me.darthwithap.api.models.entities.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StockDto(
    @Json(name = "brand")
    val brand: String,
    @Json(name = "code")
    val code: String,
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "godown")
    val godown: String,
    @Json(name = "_id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "quantity")
    val quantity: Int,
    @Json(name = "range")
    val range: String,
    @Json(name = "shop")
    val shop: String,
    @Json(name = "updatedAt")
    val updatedAt: String,
    @Json(name = "__v")
    val v: Int
)