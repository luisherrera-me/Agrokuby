package com.kubyapp.agrokuby.data.repository

import android.util.Log
import android.widget.Toast
import com.kubyapp.agrokuby.util.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kubyapp.agrokuby.data.model.user.UserInfo
import com.kubyapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) : AuthRepository {

    override fun currentUserExist(): Flow<Boolean> {
        return flow {
            emit(firebaseAuth.currentUser != null)
        }
    }
    override fun logout() {
        firebaseAuth.signOut()
    }



    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
            println(it.message.toString())
        }

    }

    override fun registerUser(email: String, password: String, username: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val userId = result.user?.uid
            val GAuthentication = false
            val user = hashMapOf(
                "username" to username,
                "photoUser" to "",
                "GAutentica" to GAuthentication
                // Agrega otros campos que quieras guardar
            )
            val usersRef = fireStore.collection("users")
            usersRef.document(userId!!)
                .set(user).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
            println(it.message.toString())
        }
    }

    override suspend fun getDataUser(): Flow<Resource<UserInfo>> = flow {
        try {
            val firebaseAuth = FirebaseAuth.getInstance()
            val currentUser = firebaseAuth.currentUser
            val userId = currentUser?.uid
            val UserStatus = userId?.let {
                Firebase.firestore
                    .collection("users")
                    .document(it)
            }
            emit(Resource.Loading())
            val documentSnapshot = UserStatus?.get()?.await()
            val UserData = documentSnapshot?.toObject(UserInfo::class.java)
            Log.d("Repository", "User data: $UserData")
            if (UserData != null) {
                emit(Resource.Success(UserData))
            } else {
                emit( Resource.Error("Data user null"))
            }
        } catch (e: FirebaseFirestoreException) {
            emit(Resource.Error(e.message.toString()))
        }

    }

override fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>> {
    return flow {
        emit(Resource.Loading())
        val result = firebaseAuth.signInWithCredential(credential).await()
        val userId = result.user?.uid
        val GAuthentication = true
        val user = hashMapOf(
            "username" to result.user?.displayName,
            "GAutentica" to GAuthentication,
            "photoUser" to result.user?.photoUrl.toString()
        )
        val usersRef = fireStore.collection("users")
        usersRef.document(userId!!)
            .set(user).await()

        emit(Resource.Success(result))
    }.catch { e ->
        emit(Resource.Error(e.message.toString()))
        println(e.message.toString())
    }
}
}