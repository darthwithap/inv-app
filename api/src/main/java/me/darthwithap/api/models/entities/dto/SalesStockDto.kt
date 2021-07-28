package me.darthwithap.api.models.entities.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SalesStockDto(
    @Json(name = "brand")
    val brand: String = "",
    @Json(name = "code")
    val code: String = "",
    @Json(name = "_id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "range")
    val range: String = ""
) {
    override fun toString(): String {
        return name
    }
}

