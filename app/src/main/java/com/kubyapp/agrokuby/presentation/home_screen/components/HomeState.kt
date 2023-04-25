package com.kubyapp.agrokuby.presentation.home_screen.components


import com.kubyapp.agrokuby.data.model.Soil

data class HomeState(
    val soil: List<Soil>? = emptyList(),
    val isLoading: Boolean = false,
    val isError: String = ""
)
