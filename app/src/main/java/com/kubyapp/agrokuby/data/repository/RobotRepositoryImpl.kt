package com.kubyapp.agrokuby.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.rpc.context.AttributeContext
import com.kubyapp.agrokuby.data.model.LightNess
import com.kubyapp.agrokuby.util.Resource
import com.kubyapp.agrokuby.data.model.RobotStatus.BatterryRobot
import com.kubyapp.domain.repository.StatusRobot
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RobotRepositoryImpl @Inject constructor(
): StatusRobot{
    private val batterryStatus = Firebase.firestore
        .collection("Robots")
        .document("17807108")
        .collection("Battery")
    override suspend fun getBatery(): Resource<List<BatterryRobot>>{
        val result: List<BatterryRobot>
        return try {
            result = batterryStatus.get().await().map {
                it.toObject(BatterryRobot::class.java)
            }
            Log.d("Repository", "Lightness query result: ${result.size}")
            Resource.Success(result)
        }catch (e: FirebaseFirestoreException){
            Resource.Error(e.message.toString())
        }
    }

}