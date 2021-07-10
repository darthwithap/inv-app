package me.darthwithap.invapp.data.models

data class StockItem(
    val name: String,
    val range: String,
    val brand: String,
    var qty: Int = 0,
    var code: String? = null,
    val shop: String,
    val godowns: List<Godown>? = null
)
