package com.kubyapp.agrokuby.presentation.home_screen.components.lightness_screen

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
class LightnessViewModel  @Inject constructor(
    private val sensorsRepository: SensorsRepository
):ViewModel() {

    private val _getLightness: MutableStateFlow<LightnessState> = MutableStateFlow(LightnessState())
    val getLightness: StateFlow<LightnessState> = _getLightness



    init {
        getAllLightness()
    }

    private fun getAllLightness() = viewModelScope.launch {
        sensorsRepository.getLightness().let { result ->
            when (result) {
                is Resource.Success -> {
                    _getLightness.value = LightnessState(lightNess = result.data)
                }

                is Resource.Loading -> {
                    _getLightness.value = LightnessState(isLoading = true)
                }

                is Resource.Error -> {
                    _getLightness.value = LightnessState(isError = result.message.toString())
                }
            }
        }
    }


}