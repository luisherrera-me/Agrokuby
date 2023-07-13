package com.kubyapp.agrokuby.presentation.home_screen.components.Soil_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kubyapp.agrokuby.util.Resource
import com.kubyapp.domain.repository.SensorsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TemperatureViewModel @Inject constructor(
    private val sensorsRepository: SensorsRepository
) : ViewModel() {

    private val _getTemperatureState: MutableStateFlow<TemperatureState> =
        MutableStateFlow(TemperatureState())
    val getTemperatureState: StateFlow<TemperatureState> = _getTemperatureState


    init {
        getAllSoilQuality()
    }

    private fun getAllSoilQuality() = viewModelScope.launch {
        sensorsRepository.getSoilQuality().let { result ->
            when (result) {
                is Resource.Error -> {
                    _getTemperatureState.value = TemperatureState(isError = result.message.toString())
                }

                is Resource.Success -> {
                    _getTemperatureState.value = TemperatureState(temperature  = result.data)
                }

                is Resource.Loading -> {
                    _getTemperatureState.value = TemperatureState(isLoading = true)
                }
            }
        }
    }


}