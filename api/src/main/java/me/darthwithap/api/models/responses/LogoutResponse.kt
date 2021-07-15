package me.darthwithap.api.models.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.darthwithap.api.models.entities.data.Data
import me.darthwithap.api.models.entities.data.StockHistoryData

@JsonClass(generateAdapter = true)
data class LogoutResponse(
    @Json(name = "data")
    val data: Data,
    @Json(name = "error")
    val error: Boolean,
    @Json(name = "message")
    val message: String,
    @Json(name = "status")
    val status: Int
)