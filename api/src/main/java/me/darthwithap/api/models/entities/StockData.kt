package me.darthwithap.api.models.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class StockData(
    @Json(name = "itemCount")
    val itemCount: Int,
    @Json(name = "pageCount")
    val pageCount: Int,
    @Json(name = "pages")
    val pages: List<PageDto>,
    @Json(name = "stock")
    val stock: List<StockDto>
)