package me.darthwithap.api.models.entities.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.darthwithap.api.models.entities.dto.PageDto
import me.darthwithap.api.models.entities.dto.StockItemDto

@JsonClass(generateAdapter = true)
data class StockSearchData(
    @Json(name = "itemCount")
    val itemCount: Int,
    @Json(name = "pageCount")
    val pageCount: Int,
    @Json(name = "pages")
    val pages: List<PageDto>,
    @Json(name = "stockItems")
    val stockItems: List<StockItemDto>
)