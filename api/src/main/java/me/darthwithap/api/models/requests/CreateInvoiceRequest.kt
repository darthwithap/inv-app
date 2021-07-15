package me.darthwithap.api.models.requests


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.darthwithap.api.models.entities.data.ProductData

@JsonClass(generateAdapter = true)
data class CreateInvoiceRequest(
    @Json(name = "name")
    val name: String,
    @Json(name = "products")
    val products: List<ProductData>
)