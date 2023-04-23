package com.kubyapp.agrokuby.presentation.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kubyapp.agrokuby.data.model.LightNess
import com.kubyapp.agrokuby.presentation.home_screen.components.HomState
import com.kubyapp.agrokuby.util.Resource
import com.kubyapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _getLightness: MutableStateFlow<HomState> = MutableStateFlow(HomState())
    val getLightness: StateFlow<HomState> = _getLightness

    init {
        getAllCoffee()
    }

    private fun getAllCoffee() = viewModelScope.launch {
        authRepository.getLightness().let { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("results", "getAllCoffee: ${result.data}")
                    _getLightness.value = HomState(lightNess = result.data)
                }

                is Resource.Loading -> {
                    _getLightness.value = HomState(isLoading = true)
                }

                is Resource.Error -> {
                    _getLightness.value = HomState(isError = result.message.toString())
                }
            }
        }
    }


}