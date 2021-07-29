package me.darthwithap.invapp.ui.login

import me.darthwithap.invapp.data.domain.models.User

data class LoginResult(
    val success: User? = null,
    val error: String? = null
)