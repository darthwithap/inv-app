package me.darthwithap.api.models.entities.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InvoiceDto(
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "customerName")
    val customerName: String,
    @Json(name = "_id")
    val id: String,
    @Json(name = "products")
    val products: List<ProductDto>,
    @Json(name = "shop")
    val shop: String,
    @Json(name = "updatedAt")
    val updatedAt: String,
    @Json(name = "user")
    val user: String,
    @Json(name = "__v")
    val v: Int
)