package me.darthwithap.api.models.entities.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.darthwithap.api.models.entities.dto.PageDto
import me.darthwithap.api.models.entities.dto.StockHistoryDto

@JsonClass(generateAdapter = true)
data class StockHistoryData(
    @Json(name = "itemCount")
    val itemCount: Int,
    @Json(name = "pageCount")
    val pageCount: Int,
    @Json(name = "pages")
    val pages: List<PageDto>,
    @Json(name = "stockHistory")
    val stockHistory: List<StockHistoryDto>
)