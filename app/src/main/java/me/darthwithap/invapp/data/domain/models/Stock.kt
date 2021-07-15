package me.darthwithap.invapp.data.domain.models


data class Stock(
    val brand: String,
    val code: String,
    val godown: String,
    val id: String,
    val name: String,
    val quantity: Int,
    val range: String,
    val shop: String,
)
