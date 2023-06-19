package com.kubyapp.domain.repository

import com.kubyapp.agrokuby.data.model.RobotStatus.BatterryRobot
import com.kubyapp.agrokuby.data.model.RobotStatus.WidgetRobot
import com.kubyapp.agrokuby.data.model.user.UserInfo
import com.kubyapp.agrokuby.util.Resource
import kotlinx.coroutines.flow.Flow

interface StatusRobot {
    suspend fun getBattery(): Flow<Resource<List<BatterryRobot>>>
    suspend fun getWidget(): Flow<Resource<List<WidgetRobot>>>

}