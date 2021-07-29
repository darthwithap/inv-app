package me.darthwithap.invapp.data.domain.models

data class User(
    val createdAt: String,
    val token: String,
    val displayName: String,
    val isActivated: Boolean,
    val role: String,
    val shop: String,
    val updatedAt: String,
    val username: String
)