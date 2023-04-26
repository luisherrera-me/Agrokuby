package com.kubyapp.agrokuby.data.model

import com.google.type.DateTime
import kotlinx.serialization.Serializable


@Serializable
data class Soil(
    val moisture: Int = 0,
    val temp: Int = 0,
    //val date:  DateTime?,
    val user: String = ""
)
