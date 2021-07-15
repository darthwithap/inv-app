package me.darthwithap.invapp.data.domain.models


data class StockHistory(
    val id: String,
    val quantity: Int,
    val stock: String,
    val user: StockHistoryUser,
)