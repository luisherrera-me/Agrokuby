package com.kubyapp.agrokuby.data.model.RobotStatus

import kotlinx.serialization.Serializable

@Serializable
data class BatterryRobot (
    val BatteryCapacity : Int = 0,
    val BatteryCharging : Boolean = false,
    val BatteryFull : Boolean = false,
    val BatteryTime : Int = 0,
    val BatteryVoltage : Int = 0
        )