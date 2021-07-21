package me.darthwithap.invapp.ui.sales.result

import me.darthwithap.api.models.entities.dto.SalesInvoiceDto

data class SalesResult(
    val success: List<SalesInvoiceDto>? = null,
    val error: String? = null
)
