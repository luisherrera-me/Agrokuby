package com.kubyapp.agrokuby.presentation.home_screen.components.user_screen

import com.kubyapp.agrokuby.data.model.user.UserInfo

data class UserState(
    val User: UserInfo? = null,
    val isLoading: Boolean = false,
    val isError: String = ""
)
