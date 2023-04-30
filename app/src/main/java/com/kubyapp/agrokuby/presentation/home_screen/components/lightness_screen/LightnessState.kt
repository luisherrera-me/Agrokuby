package com.kubyapp.agrokuby.presentation.home_screen.components.lightness_screen
import com.kubyapp.agrokuby.data.model.sensors.LightNess

data class LightnessState(
    val lightNess: List<LightNess>? = emptyList(),
    val isLoading: Boolean = false,
    val isError: String = ""
)