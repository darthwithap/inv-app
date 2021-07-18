package me.darthwithap.invapp.ui.addstock.result

import me.darthwithap.invapp.data.domain.models.StockItem

data class SearchStockResult(
    val success: List<StockItem>? = null,
    val error: String? = null
)