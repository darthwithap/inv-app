package me.darthwithap.api.models.requests


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddStockRequest(
    @Json(name = "brand")
    val brand: String?,
    @Json(name = "code")
    val code: String?,
    @Json(name = "godown")
    val godownId: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "quantity")
    val quantity: Int,
    @Json(name = "range")
    val range: String?
)