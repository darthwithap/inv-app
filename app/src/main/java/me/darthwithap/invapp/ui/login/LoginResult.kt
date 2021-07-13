package me.darthwithap.invapp.ui.login

import me.darthwithap.api.models.entities.UserDto

data class LoginResult(
    val success: UserDto? = null,
    val error: String? = null
)