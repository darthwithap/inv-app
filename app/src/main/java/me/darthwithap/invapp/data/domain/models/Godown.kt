package me.darthwithap.invapp.data.domain.models

data class Godown(
    val godownId: String,
    val name: String,
    var orders: List<GodownOrder>? = null
)
