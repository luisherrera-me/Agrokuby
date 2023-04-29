package com.kubyapp.agrokuby.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.rpc.context.AttributeContext
import com.kubyapp.agrokuby.data.model.LightNess
import com.kubyapp.agrokuby.util.Resource
import com.kubyapp.agrokuby.data.model.RobotStatus.BatterryRobot
import com.kubyapp.agrokuby.ui.theme.repository.StatusRobot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
class RobotRepositoryImpl @Inject constructor(
): StatusRobot {
    private val batterryStatus = Firebase.firestore
        .collection("Robots")
        .document("17807108")
        .collection("Battery")
        .document("Data")

    override suspend fun getBattery(): Flow<Resource<List<BatterryRobot>>> = flow {
         try {
             emit(Resource.Loading())
            val documentSnapshot = batterryStatus.get().await()
            val batterryRobot = documentSnapshot.toObject(BatterryRobot::class.java)
            Log.d("Repository", "BatterryRobot data: $batterryRobot")
            if (batterryRobot != null) {
                emit(Resource.Success(listOf(batterryRobot)))
            } else {
               emit( Resource.Error("BatterryRobot data not found"))
            }
        } catch (e: FirebaseFirestoreException) {
            emit(Resource.Error(e.message.toString()))
        }
    }




}

