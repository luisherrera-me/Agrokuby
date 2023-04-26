package com.kubyapp.domain.repository

import com.kubyapp.agrokuby.data.model.RobotStatus.BatterryRobot
import com.kubyapp.agrokuby.util.Resource

interface StatusRobot {
    suspend fun getBatery(): Resource<List<BatterryRobot>>
}