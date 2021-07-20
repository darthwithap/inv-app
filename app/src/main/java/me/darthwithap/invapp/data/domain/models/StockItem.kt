package me.darthwithap.invapp.data.domain.models

data class StockItem(
    val displayName: String,
    val godown: String,
    val id: String,
    val quantity: Int
) {
    override fun toString(): String {
        return displayName
    }
}
