package com.officeyuli.kmmexampleforitfinal.ktor.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CafeResponseItem(
    val id: String,
    val address: String,
    val name: String,
    @SerialName("city") val cityName:String
)