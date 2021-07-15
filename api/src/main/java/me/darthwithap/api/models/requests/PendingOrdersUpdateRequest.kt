package me.darthwithap.api.models.requests


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PendingOrdersUpdateRequest(
    @Json(name = "id")
    val invoiceId: String,
    @Json(name = "products")
    val productIds: List<String>
)