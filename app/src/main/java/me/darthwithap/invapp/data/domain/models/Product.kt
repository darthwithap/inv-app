package me.darthwithap.invapp.data.domain.models

data class Product(
    val godown: String,
    val id: String,
    var qty: Int = 0,
    var stock: PendingStock,
    var deliveredStatus: Boolean = false
)
