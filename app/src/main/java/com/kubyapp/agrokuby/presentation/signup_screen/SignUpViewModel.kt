package com.kubyapp.agrokuby.presentation.signup_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import com.google.firebase.auth.AuthCredential
import com.kubyapp.agrokuby.ui.theme.repository.AuthRepository
import com.kubyapp.agrokuby.presentation.login_screen.GoogleSignInState
import com.kubyapp.agrokuby.presentation.login_screen.SignInState
import com.kubyapp.agrokuby.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    val _signInState  = Channel<SignInState>()
    val signInState  = _signInState.receiveAsFlow()

    val _googleState = mutableStateOf(GoogleSignInState())
    val googleState: State<GoogleSignInState> = _googleState

    fun googleSignIn(credential: AuthCredential) = viewModelScope.launch {
        repository.googleSignIn(credential).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _googleState.value = GoogleSignInState(success = result.data)
                }
                is Resource.Loading -> {
                    _googleState.value = GoogleSignInState(loading = true)
                }
                is Resource.Error -> {
                    _googleState.value = GoogleSignInState(error = result.message!!)
                }
            }


        }
    }

    fun registerUser(email:String, password:String,username:String) = viewModelScope.launch {
        repository.registerUser(email, password,username).collect{result ->
            when(result){
                is Resource.Success ->{
                    _signInState.send(SignInState(isSuccess = "Regístrese con éxito"))
                }
                is Resource.Loading ->{
                    _signInState.send(SignInState(isLoading = true))
                }
                is Resource.Error ->{

                    _signInState.send(SignInState(isError = result.message))
                }
            }

        }
    }

}