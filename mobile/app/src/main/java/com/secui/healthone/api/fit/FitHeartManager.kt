package com.secui.healthone.api.fit

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field

class FitHeartManager  {
    companion object {
        fun readHeartPoint(context:Context):MutableState<Int>{
            val bpmValue:MutableState<Int> = mutableStateOf(-1)
            // 심박수 불러오기
            Fitness.getHistoryClient(context, FitAPIConfig.getGoogleSignInAccount(context))
                .readDailyTotal(DataType.TYPE_HEART_RATE_BPM)
                .addOnSuccessListener { result ->
                    val totalBpm =
                        result.dataPoints.firstOrNull()?.getValue(Field.FIELD_BPM)?.asInt() ?: 0
                    bpmValue.value = totalBpm;
                }
                .addOnFailureListener { e ->
                    // Log.i(FitAPIConfig.ERR_TAG, "There was a problem getting steps.", e)
                }
            return bpmValue;
        }
    }
}