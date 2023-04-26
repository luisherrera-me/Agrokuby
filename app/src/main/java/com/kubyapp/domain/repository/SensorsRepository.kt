package com.kubyapp.domain.repository

import com.kubyapp.agrokuby.data.model.LightNess
import com.kubyapp.agrokuby.data.model.Soil
import com.kubyapp.agrokuby.util.Resource

interface SensorsRepository {

    suspend fun getLightness(): Resource<List<LightNess>>
    suspend fun getSoilQuality(): Resource<List<Soil>>
}