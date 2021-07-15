package me.darthwithap.api.models.entities.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductData(
    @Json(name = "godown")
    val godown: String,
    @Json(name = "quantity")
    val quantity: Int,
    @Json(name = "stock")
    val stock: String
)