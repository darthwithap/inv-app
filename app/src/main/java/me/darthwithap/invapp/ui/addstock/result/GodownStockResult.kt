package me.darthwithap.invapp.ui.addstock.result

import me.darthwithap.invapp.data.domain.models.Stock

data class GodownStockResult(
    val success: List<Stock>? = null,
    val error: String? = null
)