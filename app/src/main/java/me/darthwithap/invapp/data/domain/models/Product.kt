package me.darthwithap.invapp.data.domain.models

data class Product(
    val godown: String,
    val id: String,
    val qty: Int = 0,
    val stock: PendingStock,
    val deliveredStatus: Boolean
)
