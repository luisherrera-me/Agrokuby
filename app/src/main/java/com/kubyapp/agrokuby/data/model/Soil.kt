package com.kubyapp.agrokuby.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Soil(
    val mositure: Int = 0,
    val temp: Int = 0,
    val user: String = ""
)
