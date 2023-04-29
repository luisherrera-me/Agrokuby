package com.kubyapp.agrokuby.presentation.home_screen.components.BarometricData
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
class BarometricViewModel @Inject constructor(
    private val sensorsRepository: SensorsRepository
): ViewModel(){
    private val _getBarometricState: MutableStateFlow<BarometricState> = MutableStateFlow(BarometricState())
    val getBarometricState: StateFlow<BarometricState> = _getBarometricState

    init {
        getBarometric()
    }

    private fun getBarometric() = viewModelScope.launch {
        sensorsRepository.getBarometric().let { result ->
            when (result) {
                is Resource.Success -> {
                    _getBarometricState.value = BarometricState(Barometric = result.data)
                }

                is Resource.Loading -> {
                    _getBarometricState.value = BarometricState(isLoading = true)
                }

                is Resource.Error -> {
                    _getBarometricState.value = BarometricState(isError = result.message.toString())
                }
            }
        }
    }
}