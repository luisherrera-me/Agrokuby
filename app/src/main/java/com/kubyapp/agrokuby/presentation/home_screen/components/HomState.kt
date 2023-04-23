package com.kubyapp.agrokuby.presentation.home_screen.components

import com.kubyapp.agrokuby.data.model.LightNess

data class HomState(
    val lightNess: List<LightNess>? = emptyList(),
    val isLoading: Boolean = false,
    val isError: String = ""
)
