package me.darthwithap.invapp.ui.addstock.result

import me.darthwithap.invapp.data.domain.models.Stock

data class AddStockResult(
    val success: Stock? = null,
    val error: String? = null
)