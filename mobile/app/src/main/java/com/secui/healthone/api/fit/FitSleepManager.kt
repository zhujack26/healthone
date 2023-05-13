package com.secui.healthone.api.fit

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessActivities
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Session
import com.google.android.gms.fitness.request.SessionInsertRequest
import com.google.android.gms.fitness.request.SessionReadRequest
import com.google.android.gms.tasks.Task
import com.secui.healthone.api.fit.FitAPIConfig.Companion.MY_TAG
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.concurrent.TimeUnit

class FitSleepManager {
    companion object {

        fun writeSleepValue(context:Context){
            val endTime = LocalDateTime.now().atZone(ZoneId.systemDefault())
            val startTime = endTime.minusHours(8)

            val fitnessOptions = FitnessOptions.builder() // 옵션
                .accessSleepSessions(FitnessOptions.ACCESS_WRITE)
                .build()

            val account = GoogleSignIn.getAccountForExtension(context, fitnessOptions) // 계정

            // Create the sleep session
            val session = Session.Builder()
                .setName("sessionName")
                .setIdentifier("identifier")
                .setDescription("description")
                .setStartTime(startTime.toEpochSecond(), TimeUnit.MILLISECONDS)
                .setEndTime(endTime.toEpochSecond(), TimeUnit.MILLISECONDS)
                .setActivity(FitnessActivities.SLEEP)
                .build()

            // Build the request to insert the session.
            val request = SessionInsertRequest.Builder()
                .setSession(session)
                .build()

            // Insert the session into Fit platform
            // Log.i(MY_TAG, "Inserting the session with the SessionsClient")

            Fitness.getSessionsClient(context, account)
                .insertSession(request)
                .addOnSuccessListener {
                    // Log.i(FitAPIConfig.MY_TAG,"Session insert was successful!")
                }
                .addOnFailureListener { e ->
                    Log.w(FitAPIConfig.ERR_TAG, "There was a problem inserting the session", e)
                }

            }

        fun readSleepValue(
            context:Context,
            account:GoogleSignInAccount = FitAPIConfig.getGoogleSignInAccount(context),
            timeMills:Long = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()):MutableState<Int> { // 입력 안올 경우 자동 init

            //  수면 측정 값
            val sleepValue:MutableState<Int> = mutableStateOf(-1);

            val endTime = LocalDateTime.now().atZone(ZoneId.systemDefault())
            val startTime = endTime.minusDays(1)

            val session = Session.Builder()
                .setName("sessionName")
                .setIdentifier("identifier")
                .setDescription("description")
                .setStartTime(timeMills, TimeUnit.MILLISECONDS)
                .build()

            val response: Task<Void> =
                Fitness.getSessionsClient(context, account).startSession(session)

            val SLEEP_STAGE_NAMES = arrayOf(
                "Unused",
                "Awake (during sleep)",
                "Sleep",
                "Out-of-bed",
                "Light sleep",
                "Deep sleep",
                "REM sleep"
            )

            val request = SessionReadRequest.Builder()
                .readSessionsFromAllApps()
                .includeSleepSessions()
                .read(DataType.TYPE_SLEEP_SEGMENT)
                .setTimeInterval(startTime.toEpochSecond(), endTime.toEpochSecond(), TimeUnit.MILLISECONDS)
                .build()

            val sessionsClient = Fitness.getSessionsClient(context, account)

            sessionsClient.readSession(request)
                .addOnSuccessListener { response ->
                    // Log.d(MY_TAG, "수면 값을 읽어옴 | ${response.sessions.toString()}")

                    for (session in response.sessions) {
                        val sessionStart = session.getStartTime(TimeUnit.MILLISECONDS)
                        val sessionEnd = session.getEndTime(TimeUnit.MILLISECONDS)
                        Log.i(MY_TAG, "FIT API 로부터 불러온 수면 측정 값은 $sessionStart ~ $sessionEnd")

                        sleepValue.value = (sessionEnd-sessionStart).toInt();

//                        val dataSets = response.getDataSet(session)
//                        Log.i(MY_TAG, "데이터 셋은 : ${dataSets.toString()}")
//
//                        for (dataSet in dataSets) {
//                            for (point in dataSet.dataPoints) {
//                                val sleepStageVal = point.getValue(Field.FIELD_SLEEP_SEGMENT_TYPE).asInt()
//                                val sleepStage = SLEEP_STAGE_NAMES[sleepStageVal]
//                                val segmentStart = point.getStartTime(TimeUnit.MILLISECONDS)
//                                val segmentEnd = point.getEndTime(TimeUnit.MILLISECONDS)
//                                Log.i(MY_TAG, "\t* Type $sleepStage between $segmentStart and $segmentEnd")
//                        }
//                    }
                }
            }
            return sleepValue
        }
    }
}
