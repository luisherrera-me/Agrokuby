package com.kubyapp.agrokuby.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kubyapp.agrokuby.util.Resource
import com.kubyapp.agrokuby.data.model.RobotStatus.BatterryRobot
import com.kubyapp.domain.repository.StatusRobot
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RobotRepositoryImpl @Inject constructor(
): StatusRobot {
    private val batterryStatus = Firebase.firestore
        .collection("Robots")
        .document("17807108")
        .collection("Battery")
        .document("Data")

    override suspend fun getBattery(): Resource<List<BatterryRobot>> {
        return try {
            val documentSnapshot = batterryStatus.get().await()
            val batterryRobot = documentSnapshot.toObject(BatterryRobot::class.java)
            Log.d("Repository", "BatterryRobot data: $batterryRobot")
            if (batterryRobot != null) {
                Resource.Success(listOf(batterryRobot))
            } else {
                Resource.Error("BatterryRobot data not found")
            }
        } catch (e: FirebaseFirestoreException) {
            Resource.Error(e.message.toString())
        }
    }


}