package com.kubyapp.agrokuby.ui.theme.repository

import com.kubyapp.agrokuby.data.model.RobotStatus.BatterryRobot
import com.kubyapp.agrokuby.util.Resource

interface StatusRobot {
    suspend fun getBattery(): Resource<List<BatterryRobot>>
}