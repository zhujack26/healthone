package com.secui.healthone.util

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import com.google.android.gms.fitness.request.DataReadRequest
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit

class FitWalkManager {
    companion object {
        fun readWalkSteps(context:Context, account:GoogleSignInAccount):MutableState<Int>{
            val walkValue = mutableStateOf(-1) // 걸음 수

            // 걸음수 불러오기
            Fitness.getHistoryClient(context, account)
                .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA)
                .addOnSuccessListener { result ->
                    val totalSteps:Int =
                        result.dataPoints.firstOrNull()?.getValue(Field.FIELD_STEPS)?.asInt() ?: 0
                    walkValue.value = totalSteps;
                }
                .addOnFailureListener { e ->
                    Log.i(FitAPIConfig.ERR_TAG, "There was a problem getting steps.", e)
                }

            return walkValue;
        }

        fun readDistanceData(context: Context, account: GoogleSignInAccount): MutableState<Float> {
            val distanceValue = mutableStateOf(0f)

            val cal = Calendar.getInstance()
            val now = Date()
            cal.time = now
            val endTime = cal.timeInMillis
            cal.add(Calendar.DAY_OF_YEAR, -7)
            val startTime = cal.timeInMillis

            val readRequest = DataReadRequest.Builder()
                .aggregate(DataType.TYPE_DISTANCE_DELTA, DataType.AGGREGATE_DISTANCE_DELTA)
                .bucketByTime(1, TimeUnit.DAYS)
                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                .build()

            Fitness.getHistoryClient(context, account)
                .readData(readRequest)
                .addOnSuccessListener { response ->
                    // Process the response here
                    val buckets = response.buckets
                    var totalDistance = 0f
                    for (bucket in buckets) {
                        val dataSets = bucket.dataSets
                        for (dataSet in dataSets) {
                            for (dp in dataSet.dataPoints) {
                                for (field in dp.dataType.fields) {
                                    val value = dp.getValue(field)
                                    totalDistance += value.asFloat()
                                }
                            }
                        }
                    }
                    distanceValue.value = totalDistance
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Failed to read distance data", e)
                }

            return distanceValue
        }
    }
}