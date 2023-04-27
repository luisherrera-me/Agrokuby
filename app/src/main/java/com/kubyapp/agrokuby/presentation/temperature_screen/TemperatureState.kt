package com.kubyapp.agrokuby.presentation.temperature_screen

import com.kubyapp.agrokuby.data.model.Soil

data class TemperatureState(
    val temperature: List<Soil>? = emptyList(),
    val isLoading: Boolean = false,
    val isError: String = ""
)