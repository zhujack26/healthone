package com.secui.healthone.util

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import com.google.android.gms.fitness.request.DataReadRequest
import com.secui.healthone.util.FitAPIConfig.Companion.MY_TAG
import java.util.concurrent.TimeUnit

class FitNutritionManager {
    companion object {
        fun readNutritionData(context: Context): MutableState<Int> {
             val bpmValue: MutableState<Int> = mutableStateOf(0)
            // 영양정보 불러오기
//            Fitness.getHistoryClient(context, FitAPIConfig.getGoogleSignInAccount(context))
//                .readDailyTotal(DataType.AGGREGATE_NUTRITION_SUMMARY)
//                .addOnSuccessListener { result ->
//                    val totalNutrition =
//                        result.dataPoints.firstOrNull()?.getValue(Field.FIELD_NUTRIENTS)?.asInt() ?: 0
//                    // bpmValue.value = totalBpm;
//                    Log.d(MY_TAG, "영앙정보는=====================")
//                    Log.d(MY_TAG, " Nutrition : ${totalNutrition}")
//                }
//                .addOnFailureListener { e ->
//                    Log.i(FitAPIConfig.ERR_TAG, "There was a problem getting steps.", e)
//                }

//            val readRequest = DataReadRequest.Builder()
//                .aggregate(DataType.AGGREGATE_NUTRITION_SUMMARY)
//                .bucketByTime(1, TimeUnit.DAYS)
//                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
//                .build()

            return bpmValue;
        }

    }
}