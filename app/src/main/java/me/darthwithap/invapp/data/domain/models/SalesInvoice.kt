package me.darthwithap.invapp.data.domain.models

data class SalesInvoice(
    val createdAt: String,
    val customerName: String,
    val id: String,
    val products: List<SalesProduct>,
    val shop: String,
    val updatedAt: String,
    val user: String,
)