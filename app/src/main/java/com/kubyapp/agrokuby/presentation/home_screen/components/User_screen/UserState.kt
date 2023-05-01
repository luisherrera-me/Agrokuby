package com.kubyapp.agrokuby.presentation.home_screen.components.User_screen

import com.kubyapp.agrokuby.data.model.user.UserInfo

data class UserState(
    val User: List<UserInfo>? = emptyList(),
    val isLoading: Boolean = false,
    val isError: String = ""
)
