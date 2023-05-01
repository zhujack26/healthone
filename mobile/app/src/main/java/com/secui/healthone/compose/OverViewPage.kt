package com.secui.healthone.compose

import android.Manifest
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataPoint
import com.google.android.gms.fitness.data.DataSet
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import com.google.android.gms.fitness.request.DataReadRequest
import com.secui.healthone.MainActivity
import com.secui.healthone.ui.common.TopBar
import com.secui.healthone.ui.overviewpage.*
import com.secui.healthone.util.PageRoutes
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Calendar
import java.util.concurrent.TimeUnit


const val GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 1;
lateinit var context: Context;
lateinit var fitnessOptions: FitnessOptions
const val MY_TAG = "ACCOUNT::::::"


@Composable
fun OverViewPage(
    navController: NavHostController,
    modifier: Modifier = Modifier
        .fillMaxSize()
) {

    context = LocalContext.current;
    fitnessOptions = FitnessOptions.builder()
        .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
        .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
        .addDataType(DataType.AGGREGATE_HEART_RATE_SUMMARY, FitnessOptions.ACCESS_READ)
        .build()

    val account = GoogleSignIn.getAccountForExtension(LocalContext.current, fitnessOptions)

    Log.d( MY_TAG, "${GoogleSignIn.hasPermissions(account, fitnessOptions)}")

    val thisActivity = LocalContext.current as Activity;

    // GOOGLE_FIT_PERMISSIONS_REQUEST_CODE
    if(!GoogleSignIn.hasPermissions(account, fitnessOptions)){
        GoogleSignIn.requestPermissions(
            LocalContext.current as Activity, // your activity
            GOOGLE_FIT_PERMISSIONS_REQUEST_CODE, // e.g. 1
            account,
            fitnessOptions)
    }else {

        if (ContextCompat.checkSelfPermission(thisActivity, Manifest.permission.ACTIVITY_RECOGNITION)
            != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(thisActivity,
                arrayOf(Manifest.permission.ACTIVITY_RECOGNITION, Manifest.permission.BODY_SENSORS),
                GOOGLE_FIT_PERMISSIONS_REQUEST_CODE)
        } else {

            // 걸음수 구독하기 (?)
            Fitness.getRecordingClient(context, GoogleSignIn.getAccountForExtension(context, fitnessOptions))
                .subscribe(DataType.TYPE_STEP_COUNT_CUMULATIVE)
                .addOnSuccessListener {
                    Log.i( MY_TAG,"STEP_COUNT_CUMULATIVE Subscription was successful!")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "There was a problem subscribing ", e)
                }

            // 심박수 구독하기 (?)
            Fitness.getRecordingClient(context, GoogleSignIn.getAccountForExtension(context, fitnessOptions))
                .subscribe(DataType.TYPE_HEART_RATE_BPM)
                .addOnSuccessListener {
                    Log.i( MY_TAG,"HEART_RATE_BPM Subscription was successful!")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "There was a problem subscribing ", e)
                }
        }



        // 걸음수 불러오기
        Fitness.getHistoryClient(context, GoogleSignIn.getAccountForExtension(context, fitnessOptions))
            .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA)
            .addOnSuccessListener { result ->
                val totalSteps =
                    result.dataPoints.firstOrNull()?.getValue(Field.FIELD_STEPS)?.asInt() ?: 0
                // Do something with totalSteps
                Log.d( MY_TAG,"${totalSteps}")
                Log.d( MY_TAG, "${result.dataPoints.firstOrNull()?.getValue(Field.FIELD_STEPS)}")

            }
            .addOnFailureListener { e ->
                Log.i(TAG, "There was a problem getting steps.", e)
            }

        // 심박수 불러오기
        Fitness.getHistoryClient(context, GoogleSignIn.getAccountForExtension(context, fitnessOptions))
            .readDailyTotal(DataType.TYPE_HEART_RATE_BPM)
            .addOnSuccessListener { result ->
                val totalBpm =
                    result.dataPoints.firstOrNull()?.getValue(Field.FIELD_BPM)?.asInt() ?: 0
                // Do something with totalSteps
                Log.d( MY_TAG,"${totalBpm}")
                Log.d( MY_TAG, "${result.dataPoints.firstOrNull()?.getValue(Field.FIELD_BPM)}")
            }
            .addOnFailureListener { e ->
                Log.i(TAG, "There was a problem getting steps.", e)
            }

    }


    Column(modifier= Modifier
        .fillMaxSize()
        .verticalScroll
            (rememberScrollState()))
    {

        TotalHealthBox();
        UserWalkBox(navController);
        HeartRateBox(navController);
        StressIndexBox(navController);
        FoodCalorieBox(navController);
        SleepCheckBox(navController);
        HealthScoreBox(navController);
        Spacer(modifier = Modifier.height(64.dp));

    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun accessGoogleFit() {
    val end = LocalDateTime.now()
    val start = end.minusYears(1)
    val endSeconds = end.atZone(ZoneId.systemDefault()).toEpochSecond()
    val startSeconds = start.atZone(ZoneId.systemDefault()).toEpochSecond()
    val readRequest = DataReadRequest.Builder()
        .aggregate(DataType.AGGREGATE_STEP_COUNT_DELTA)
        .setTimeRange(startSeconds, endSeconds, TimeUnit.SECONDS)
        .bucketByTime(1, TimeUnit.DAYS)
        .build()
    val account = GoogleSignIn.getAccountForExtension(context, fitnessOptions)
    Fitness.getHistoryClient(context, account)
        .readData(readRequest)
        .addOnSuccessListener({ response ->
            // Use response data here
            Log.i(TAG, "OnSuccess()")
        })
        .addOnFailureListener({ e -> Log.d(TAG, "OnFailure()", e) })
}
