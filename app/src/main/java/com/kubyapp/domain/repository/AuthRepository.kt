package com.kubyapp.domain.repository

import com.kubyapp.agrokuby.util.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.kubyapp.agrokuby.data.model.user.UserInfo
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(email: String, password: String, username:String): Flow<Resource<AuthResult>>

    fun currentUserExist(): Flow<Boolean>

    fun logout()

    suspend fun getDataUser():Flow<Resource<List<UserInfo>>>

    fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>>
}