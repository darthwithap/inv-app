package me.darthwithap.api.models.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.darthwithap.api.models.entities.dto.NewGodownDto

@JsonClass(generateAdapter = true)
data class NewGodownResponse(
    @Json(name = "data")
    val data: NewGodownDto,
    @Json(name = "error")
    val error: Boolean,
    @Json(name = "message")
    val message: String,
    @Json(name = "status")
    val status: Int
)