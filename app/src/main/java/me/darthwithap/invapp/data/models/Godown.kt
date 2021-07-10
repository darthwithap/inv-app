package me.darthwithap.invapp.data.models

data class Godown(
    val code: String,
    val name: String,
    var orders: List<GodownOrder>? = null
)
