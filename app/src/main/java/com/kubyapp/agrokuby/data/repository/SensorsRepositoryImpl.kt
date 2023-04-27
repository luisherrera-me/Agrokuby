package com.kubyapp.agrokuby.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kubyapp.agrokuby.data.model.LightNess
import com.kubyapp.agrokuby.data.model.Soil
import com.kubyapp.agrokuby.util.Resource
import com.kubyapp.domain.repository.SensorsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SensorsRepositoryImpl @Inject constructor(

) : SensorsRepository{
    private val lightnessRef = Firebase.firestore
        .collection("sensors")
        .document("brightness")
        .collection("liveLux")

    override suspend fun getLightness(): Resource<List<LightNess>> {
        val result: List<LightNess>
        return try {
            result = lightnessRef.get().await().map {
                it.toObject(LightNess::class.java)
            }

            Log.d("Repository", "Lightness query result: ${result.size}")

            Resource.Success(result)
        } catch (e: FirebaseFirestoreException) {
            Resource.Error(e.message.toString())
        }
    }
    private val soilRef = Firebase.firestore
        .collection("sensors")
        .document("soilQuality")
        .collection("liveSoil")

    override suspend fun getSoilQuality(): Resource<List<Soil>> {
        val result: List<Soil>
        return try {
            result = soilRef.get().await().map {
                it.toObject(Soil::class.java)
            }
            Log.d("Repository", "Soil query result: ${result.size}")
            Resource.Success(result)
        } catch (e: FirebaseFirestoreException) {
            Resource.Error(e.message.toString())
        }
    }
}