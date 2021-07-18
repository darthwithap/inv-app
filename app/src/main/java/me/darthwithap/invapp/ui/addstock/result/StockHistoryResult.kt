package me.darthwithap.invapp.ui.addstock.result

import me.darthwithap.invapp.data.domain.models.StockHistory

data class StockHistoryResult(
    val success: List<StockHistory>? = null,
    val error: String? = null
)