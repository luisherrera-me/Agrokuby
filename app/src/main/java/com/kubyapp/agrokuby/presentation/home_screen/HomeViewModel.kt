package com.kubyapp.agrokuby.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kubyapp.agrokuby.presentation.home_screen.components.HomeState
import com.kubyapp.agrokuby.util.Resource
import com.kubyapp.domain.repository.SensorsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sensorsRepository: SensorsRepository
) : ViewModel() {

    private val _homeState: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState

    init {
            getAllSoilQuality()

    }


    private fun getAllSoilQuality() = viewModelScope.launch {
        sensorsRepository.getSoilQuality().let { result ->
            when (result) {
                is Resource.Success -> {
                    _homeState.value = HomeState(soil = result.data)
                }
                is Resource.Loading -> {
                    _homeState.value = HomeState(isLoading = true)
                }
                is Resource.Error -> {
                    _homeState.value = HomeState(isError = result.message.toString())
                }
            }
        }
    }




}