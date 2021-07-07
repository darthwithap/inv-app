package me.darthwithap.invapp.ui.login

import me.darthwithap.api.models.entities.User

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: User? = null,
    val error: String? = null
)