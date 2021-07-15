package me.darthwithap.invapp.ui.orders

import me.darthwithap.invapp.data.domain.models.Invoice

data class PendingOrdersUpdateStatusResult(
    val success: String? = null,
    val error: String? = null
)