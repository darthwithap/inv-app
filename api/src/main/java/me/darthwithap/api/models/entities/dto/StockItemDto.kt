package me.darthwithap.api.models.entities.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StockItemDto(
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "godown")
    val godown: String,
    @Json(name = "_id")
    val id: String,
    @Json(name = "quantity")
    val quantity: Int
)