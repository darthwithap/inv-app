package me.darthwithap.invapp.data.domain.models

data class GodownOrder(
    val customerName: String,
    val username: String,
    var products: List<Product>
)
