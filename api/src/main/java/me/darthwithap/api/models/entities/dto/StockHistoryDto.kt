package me.darthwithap.api.models.entities.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StockHistoryDto(
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "_id")
    val id: String,
    @Json(name = "quantity")
    val quantity: Int,
    @Json(name = "stock")
    val stock: String,
    @Json(name = "updatedAt")
    val updatedAt: String,
    @Json(name = "user")
    val user: StockHistoryUserDto,
    @Json(name = "__v")
    val v: Int
)