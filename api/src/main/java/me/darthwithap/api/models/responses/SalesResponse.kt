package me.darthwithap.api.models.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.darthwithap.api.models.entities.data.SalesData

@JsonClass(generateAdapter = true)
data class SalesResponse(
    @Json(name = "data")
    val data: SalesData,
    @Json(name = "error")
    val error: Boolean,
    @Json(name = "message")
    val message: String,
    @Json(name = "status")
    val status: Int
)