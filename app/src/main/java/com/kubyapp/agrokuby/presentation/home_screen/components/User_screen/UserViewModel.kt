package com.kubyapp.agrokuby.presentation.home_screen.components.User_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kubyapp.agrokuby.data.repository.AuthRepositoryImpl
import com.kubyapp.agrokuby.data.repository.RobotRepositoryImpl
import com.kubyapp.agrokuby.presentation.home_screen.components.Robot_screen.getRobotStatus
import com.kubyapp.agrokuby.util.Resource
import com.kubyapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val authRepositoryImpl: AuthRepositoryImpl
):ViewModel(){

    private val _UserStatus: MutableStateFlow<UserState> =
        MutableStateFlow(UserState())
    val UserStatus: StateFlow<UserState> = _UserStatus


    init {
        getStatusUser()
    }


    private fun getStatusUser() = viewModelScope.launch {
        authRepositoryImpl.getDataUser().collectLatest { result ->
            when (result) {
                is Resource.Error -> {
                    _UserStatus.value = UserState(isError = result.message.toString())
                }

                is Resource.Success -> {
                    _UserStatus.value = UserState(User = result.data)
                }

                is Resource.Loading -> {
                    _UserStatus.value = UserState(isLoading = true)
                }
            }
        }
    }
}