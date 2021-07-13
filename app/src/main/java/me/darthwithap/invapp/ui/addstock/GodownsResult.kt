package me.darthwithap.invapp.ui.addstock

import me.darthwithap.invapp.data.domain.models.Godown

data class GodownsResult(
    val success: List<Godown>? = null,
    val error: String? = null
)