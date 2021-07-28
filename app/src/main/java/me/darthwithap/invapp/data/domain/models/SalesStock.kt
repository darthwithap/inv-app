package me.darthwithap.invapp.data.domain.models

import com.squareup.moshi.Json

data class SalesStock(
    val brand: String = "",
    val code: String = "",
    val id: String,
    val name: String,
    val range: String = ""
) {
    override fun toString(): String {
        return name
    }
}