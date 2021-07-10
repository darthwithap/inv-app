package me.darthwithap.invapp.data.models

data class OrderProductItem(
    val name: String,
    val code: String,
    var price: Float,
    val brand: String? = null,
    var qty: Int = 0,
    var selected: Boolean = false
)
