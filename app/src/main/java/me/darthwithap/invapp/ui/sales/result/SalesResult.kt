package me.darthwithap.invapp.ui.sales.result

import me.darthwithap.invapp.data.domain.models.SalesInvoice

data class SalesResult(
    val success: List<SalesInvoice>? = null,
    val error: String? = null
)
