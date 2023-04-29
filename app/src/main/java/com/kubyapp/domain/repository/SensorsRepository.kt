package com.kubyapp.domain.repository

import com.kubyapp.agrokuby.data.model.sensors.Barometric
import com.kubyapp.agrokuby.data.model.sensors.LightNess
import com.kubyapp.agrokuby.data.model.sensors.Soil
import com.kubyapp.agrokuby.util.Resource

interface SensorsRepository {

    suspend fun getLightness(): Resource<List<LightNess>>
    suspend fun getSoilQuality(): Resource<List<Soil>>
    suspend fun getBarometric(): Resource<List<Barometric>>
}