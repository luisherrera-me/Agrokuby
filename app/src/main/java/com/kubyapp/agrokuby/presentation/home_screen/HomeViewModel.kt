package com.kubyapp.agrokuby.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kubyapp.agrokuby.data.repository.RobotRepositoryImpl
import com.kubyapp.agrokuby.presentation.home_screen.components.HomeState
import com.kubyapp.agrokuby.presentation.home_screen.components.getRobotStatus
import com.kubyapp.agrokuby.util.Resource
import com.kubyapp.domain.repository.SensorsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val robotRepositoryImpl: RobotRepositoryImpl
) : ViewModel() {

    private val _getRobotStatus: MutableStateFlow<getRobotStatus> =
        MutableStateFlow(getRobotStatus())
    val getRobotStatus: StateFlow<getRobotStatus> = _getRobotStatus


    init {
        getStatusRobot()//inicializacion
    }


    private fun getStatusRobot() = viewModelScope.launch {
        robotRepositoryImpl.getBattery().let { result ->
            when (result) {
                is Resource.Error -> {
                    _getRobotStatus.value = getRobotStatus(isError = result.message.toString())
                }

                is Resource.Success -> {
                    _getRobotStatus.value = getRobotStatus(BatterryRobot = result.data)
                }

                is Resource.Loading -> {
                    _getRobotStatus.value = getRobotStatus(isLoading = true)
                }
            }
        }
    }


}