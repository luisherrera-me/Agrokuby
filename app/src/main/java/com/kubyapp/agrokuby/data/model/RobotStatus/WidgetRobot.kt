package com.kubyapp.agrokuby.data.model.RobotStatus

import kotlinx.serialization.Serializable

@Serializable
data class WidgetRobot (
    val SensBMP390 : Boolean = false,
    val Lightning : Boolean = false,
    val ambientLight : Boolean = false,
    val SensSoil : Boolean = false,
    val SensSGP30 : Boolean = false
        )