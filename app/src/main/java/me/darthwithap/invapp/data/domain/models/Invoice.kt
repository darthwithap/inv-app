package me.darthwithap.invapp.data.domain.models

data class Invoice(
    val id: String,
    val userName: String,
    val customerName: String,
    var products: List<Product>? = null,
    val shop: String
)
