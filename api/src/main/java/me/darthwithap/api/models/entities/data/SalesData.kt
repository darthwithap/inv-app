package me.darthwithap.api.models.entities.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.darthwithap.api.models.entities.dto.PageDto
import me.darthwithap.api.models.entities.dto.SalesInvoiceDto

@JsonClass(generateAdapter = true)
data class SalesData(
    @Json(name = "invoices")
    val invoices: List<SalesInvoiceDto>,
    @Json(name = "itemCount")
    val itemCount: Int,
    @Json(name = "pageCount")
    val pageCount: Int,
    @Json(name = "pages")
    val pages: List<PageDto>
)