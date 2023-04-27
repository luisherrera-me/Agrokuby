package com.kubyapp.agrokuby.data.repository

import android.util.Log
import com.kubyapp.agrokuby.util.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kubyapp.agrokuby.data.model.LightNess
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
    // Previous method for registering User with Email And Password
    /*override fun registerUser2(email: String, password: String, username: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
            println(it.message.toString())
        }
    }
     */


    override fun registerUser(email: String, password: String, username: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val userId = result.user?.uid
            val GAuthentication = false
            val userdata = hashMapOf(
                "username" to username,
                "photoUser" to "NO-PHOTO",
                "GAutentica" to GAuthentication
                // Agrega otros campos que quieras guardar
            )
            if (userId != null) {
                val userRef = fireStore.collection("users")
                    .document(userId)
                userRef.set(userdata).await()
            }
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
            println(it.message.toString())
        }
    }
// previous method for registering  User whith Google
/*
    override fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithCredential(credential).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
            println(it.message.toString())
        }
    }
 */
override fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>> {
    return flow {
        emit(Resource.Loading())
        val result = firebaseAuth.signInWithCredential(credential).await()
        val username = result.user // obtiene la información del Usuario
        val name = username?.displayName
        val photoUrl = username?.photoUrl
        val GAuthentication = true
        val userId = username?.uid

        val userdata = hashMapOf(
            "username" to username,
            "photoUser" to photoUrl,
            "GAutentica" to GAuthentication
            // Agrega otros campos que quieras guardar
        )

        if (userId != null) {
            val userRef = fireStore.collection("users")
                .document(userId)
            userRef.set(userdata).await()
        }
        emit(Resource.Success(result))
    }.catch {
        emit(Resource.Error(it.message.toString()))
        println(it.message.toString())
    }
}
}