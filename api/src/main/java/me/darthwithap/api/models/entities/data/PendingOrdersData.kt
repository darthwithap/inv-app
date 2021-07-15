package me.darthwithap.api.models.entities.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.darthwithap.api.models.entities.dto.InvoiceDto
import me.darthwithap.api.models.entities.dto.PageDto

@JsonClass(generateAdapter = true)
data class PendingOrdersData(
    @Json(name = "invoices")
    val invoices: List<InvoiceDto>,
    @Json(name = "itemCount")
    val itemCount: Int,
    @Json(name = "pageCount")
    val pageCount: Int,
    @Json(name = "pages")
    val pages: List<PageDto>
)