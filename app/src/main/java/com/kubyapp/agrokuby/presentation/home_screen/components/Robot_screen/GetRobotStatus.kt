package com.kubyapp.agrokuby.presentation.home_screen.components.Robot_screen

import com.kubyapp.agrokuby.data.model.RobotStatus.BatterryRobot

data class getRobotStatus (
    val BatterryRobot: List<BatterryRobot>? = emptyList(),
    val isLoading: Boolean = false,
    val isError: String = ""
)