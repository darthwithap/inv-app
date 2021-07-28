package me.darthwithap.invapp.data.domain.models

data class SalesProduct(
    val deliveredStatus: Boolean,
    val godown: String,
    val id: String,
    val quantity: Int,
    val stock: SalesStock
) {
    override fun toString(): String {
        return "$quantity $stock"
    }
}