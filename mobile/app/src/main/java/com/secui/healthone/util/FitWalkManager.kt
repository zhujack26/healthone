package com.secui.healthone.util

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field


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
    }
}