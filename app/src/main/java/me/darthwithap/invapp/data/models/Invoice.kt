package me.darthwithap.invapp.data.models

data class Invoice(
    val userId: String,
    val customerName: String,
    var products: List<OrderProductItem>? = null,
    var deliveryStatus: Boolean = false
)
