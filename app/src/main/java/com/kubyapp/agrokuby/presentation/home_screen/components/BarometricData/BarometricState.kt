package com.kubyapp.agrokuby.presentation.home_screen.components.BarometricData
import com.kubyapp.agrokuby.data.model.sensors.Barometric


data class BarometricState (
    val Barometric : List<Barometric>? = emptyList(),
    val isLoading: Boolean = false,
    val isError: String = ""
)