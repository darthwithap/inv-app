package me.darthwithap.invapp.data.domain.models

import java.util.*

data class Sale(
    val customerName: String,
    val sellerName: String,
    val saleDetails: String? = null,
    var date: Date = Date(System.currentTimeMillis()),
    val isNewSale: Boolean = true
)
