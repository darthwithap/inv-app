package me.darthwithap.invapp.ui.addstock

import me.darthwithap.invapp.data.domain.models.Godown

data class NewGodownResult(
    val success: Godown? = null,
    val error: String? = null
)
