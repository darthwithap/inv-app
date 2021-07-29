package me.darthwithap.api.models.entities.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductData(
    @Json(name = "godown")
    var godown: String,
    @Json(name = "quantity")
    var quantity: Int,
    @Json(name = "stock")
    var stock: String
)