package com.kubyapp.agrokuby.presentation.home_screen.components.Soil_screen

import com.kubyapp.agrokuby.data.model.sensors.Soil

data class TemperatureState(
    val temperature: List<Soil>? = emptyList(),
    val isLoading: Boolean = false,
    val isError: String = ""
)