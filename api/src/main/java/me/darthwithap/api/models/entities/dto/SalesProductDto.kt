package me.darthwithap.api.models.entities.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SalesProductDto(
    @Json(name = "deliveredStatus")
    val deliveredStatus: Boolean,
    @Json(name = "godown")
    val godown: String,
    @Json(name = "_id")
    val id: String,
    @Json(name = "quantity")
    val quantity: Int,
    @Json(name = "stock")
    val stock: SalesStockDto
) {
    override fun toString(): String {
        return "$quantity $stock"
    }
}

