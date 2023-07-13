package com.kubyapp.agrokuby.presentation.home_screen.components.Soil_screen


import com.kubyapp.agrokuby.data.model.sensors.Soil

data class HomeState(
    val soil: List<Soil>? = emptyList(),
    val isLoading: Boolean = false,
    val isError: String = ""
)
