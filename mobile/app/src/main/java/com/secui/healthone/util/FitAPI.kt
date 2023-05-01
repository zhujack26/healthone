package com.secui.healthone.util

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.request.DataReadRequest
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Calendar
import java.util.concurrent.TimeUnit


//fun doFitAPI(context:Context) {
    val fitnessOptions:FitnessOptions = FitnessOptions.builder()
        .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
        .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
        .build()
//
//    val account = GoogleSignIn.getAccountForExtension(context, fitnessOptions)
//    val GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 1;
//
//    if (!GoogleSignIn.hasPermissions(account, fitnessOptions)) {
//        GoogleSignIn.requestPermissions(
//            context, // your activity
//            GOOGLE_FIT_PERMISSIONS_REQUEST_CODE, // e.g. 1
//            account,
//            fitnessOptions
//        )
//
//    } else {
//        accessGoogleFit(LocalContext.current, fitnessOptions);
//    }
//}
//
//private fun accessGoogleFit(context: Context = LocalContext.current, fitnessOptions:FitnessOptions) {
//
//    val end = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//        LocalDateTime.now()
//    } else {
//        TODO("VERSION.SDK_INT < O")
//        // Calendar.getInstance().time
//    }
//    val start = end.minusYears(1)
//    val endSeconds = end.atZone(ZoneId.systemDefault()).toEpochSecond()
//    val startSeconds = start.atZone(ZoneId.systemDefault()).toEpochSecond()
//
//    val readRequest = DataReadRequest.Builder()
//        .aggregate(DataType.AGGREGATE_STEP_COUNT_DELTA)
//        .setTimeRange(startSeconds, endSeconds, TimeUnit.SECONDS)
//        .bucketByTime(1, TimeUnit.DAYS)
//        .build()
//    val account = GoogleSignIn.getAccountForExtension(context, fitnessOptions)
//    Fitness.getHistoryClient(context, account)
//        .readData(readRequest)
//        .addOnSuccessListener({ response ->
//            // Use response data here
//            Log.i(TAG, "OnSuccess()")
//        })
//        .addOnFailureListener({ e -> Log.d(TAG, "OnFailure()", e) })
//}
//
//

