package me.darthwithap.invapp.data.models

data class GodownOrder(
    val customerName: String,
    val username: String,
    var products: List<OrderProductItem>
)
