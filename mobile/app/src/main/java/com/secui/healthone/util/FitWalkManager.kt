package com.secui.healthone.util

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.data.DataSource
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import com.google.android.gms.fitness.request.DataReadRequest
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit
import kotlin.math.max


class FitWalkManager {
    companion object {
        // 일일 걸음수
        fun readWalkSteps(context: Context, account: GoogleSignInAccount): MutableState<Int> {
            val walkValue = mutableStateOf(0)
            Fitness.getHistoryClient(context, account)
                .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA)
                .addOnSuccessListener { result ->
                    val totalSteps: Int =
                        result.dataPoints.firstOrNull()?.getValue(Field.FIELD_STEPS)?.asInt() ?: 0
                    walkValue.value = totalSteps;
                }
                .addOnFailureListener { e ->
                    Log.i(FitAPIConfig.ERR_TAG, "There was a problem getting steps.", e)
                }

            return walkValue;
        }

        //일일 거리
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

        //누적 일일 최고 걸음 수
        fun readMaxDailySteps(context: Context, account: GoogleSignInAccount): MutableState<Int> {
            val maxSteps = mutableStateOf(0)

            val startTime = LocalDate.now().atStartOfDay(ZoneId.systemDefault())
            val endTime = LocalDateTime.now().atZone(ZoneId.systemDefault())

            val datasource = DataSource.Builder()
                .setAppPackageName("com.google.android.gms")
                .setDataType(DataType.TYPE_STEP_COUNT_DELTA)
                .setType(DataSource.TYPE_DERIVED)
                .setStreamName("estimated_steps")
                .build()

            val request = DataReadRequest.Builder()
                .aggregate(datasource)
                .bucketByTime(1, TimeUnit.DAYS)
                .setTimeRange(startTime.toEpochSecond(), endTime.toEpochSecond(), TimeUnit.SECONDS)
                .build()

            Fitness.getHistoryClient(context, account)
                .readData(request)
                .addOnSuccessListener { response ->
                    for (bucket in response.buckets) {
                        val totalSteps = bucket.dataSets
                            .flatMap { it.dataPoints }
                            .sumOf { it.getValue(Field.FIELD_STEPS).asInt() }
                        maxSteps.value = max(maxSteps.value, totalSteps)
                    }
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Failed to read steps data", e)
                }

            return maxSteps
        }

        //누적 총 걸음수
        fun readTotalSteps(context: Context, account: GoogleSignInAccount): MutableState<Int> {
            val totalSteps = mutableStateOf(0)

            val endTime = Calendar.getInstance().timeInMillis
            val startCalendar = Calendar.getInstance().apply {
                clear()
                set(2020, Calendar.JANUARY, 1)
            }
            val startTime = startCalendar.timeInMillis

            val datasource = DataSource.Builder()
                .setAppPackageName("com.google.android.gms")
                .setDataType(DataType.TYPE_STEP_COUNT_DELTA)
                .setType(DataSource.TYPE_DERIVED)
                .setStreamName("estimated_steps")
                .build()

            val readRequest = DataReadRequest.Builder()
                .aggregate(datasource)
                .bucketByTime(1, TimeUnit.DAYS)
                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                .build()

            Fitness.getHistoryClient(context, account)
                .readData(readRequest)
                .addOnSuccessListener { response ->
                    val buckets = response.buckets
                    for (bucket in buckets) {
                        val dataSets = bucket.dataSets
                        for (dataSet in dataSets) {
                            for (dp in dataSet.dataPoints) {
                                for (field in dp.dataType.fields) {
                                    val value = dp.getValue(field).asInt()
                                    totalSteps.value += value
                                }
                            }
                        }
                    }
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Failed to read steps data", e)
                }
            return totalSteps
        }
    }
}