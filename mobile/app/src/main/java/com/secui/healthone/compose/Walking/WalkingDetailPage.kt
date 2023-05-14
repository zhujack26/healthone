package com.secui.healthone.compose

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.secui.healthone.api.fit.FitWalkManager
import com.secui.healthone.constant.AppColors
import com.secui.healthone.ui.walking.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun WalkingDetailPage(navController: NavController, context: Context, account: GoogleSignInAccount, date: LocalDate) {

    val stepsData = remember { mutableStateOf(emptyList<Int>()) }
    val distanceData = remember { mutableStateOf(0f) }
    val targetSteps = 6000

    LaunchedEffect(key1 = date) {
        launch {
            stepsData.value = FitWalkManager.readHourlySteps(context, account, date)
            distanceData.value = FitWalkManager.readDistanceData(context, account).value
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(
            text = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        val totalSteps = stepsData.value.sum()
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 24.sp)) {
                    append("$totalSteps")
                }
                append("/")
                withStyle(style = SpanStyle(fontSize = 16.sp)) {
                    append("$targetSteps")
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "일일걸음",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            repeat(24) { index ->
                val steps = stepsData.value.getOrNull(index)
                val barHeight = steps?.toFloat()?.times(0.07f) ?: 0f

                Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier.height(100.dp)) {
                    Box(
                        modifier = Modifier
                            .height(barHeight.dp)
                            .width(8.dp)
                            .background(color = AppColors.green400)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .width(8.dp)
                            .height(8.dp)
                            .background(color = AppColors.mono400, shape = CircleShape)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "거리: ${"%.2f".format(distanceData.value)} km",
            fontSize = 16.sp,
        )
    }
}