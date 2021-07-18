package me.darthwithap.invapp.ui.orders.result

import me.darthwithap.invapp.data.domain.models.Invoice

data class PendingOrdersResult(
    val success: List<Invoice>? = null,
    val error: String? = null
)