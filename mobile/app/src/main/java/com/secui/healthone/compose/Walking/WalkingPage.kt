package com.secui.healthone.compose

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.secui.healthone.data.WalkData
import com.secui.healthone.ui.walking.*
import com.secui.healthone.util.FitAPIConfig
import com.secui.healthone.util.FitWalkManager
import com.secui.healthone.viewmodel.WalkViewModel
import retrofit2.Response


@Composable
fun WalkingPage(
    navController: NavController,
    highestSteps: Int = 5000,
    totalSteps: Int = 10000){
    val context = LocalContext.current
    val walkValue: State<Int> = remember {
        FitWalkManager.readWalkSteps(
            context,
            FitAPIConfig.getGoogleSignInAccount(context = context)
        )
    }
    val todaySteps = walkValue.value


    // 걷은 거리
    val distanceValue: State<Float> = remember {
        FitWalkManager.readDistanceData(
            context,
            FitAPIConfig.getGoogleSignInAccount(context = context)
        )
    }
    val todayDistance = distanceValue.value
    val viewModel: WalkViewModel = viewModel()
    val walkData = WalkData(
        userNo = 1, // 실제 사용자 번호로
        stepCount = todaySteps,
        distance = todayDistance.toDouble(), // 구글 Fit API에서 distance 값을 가져오거나 빈 값 사용
    )
    fun handleResponse(response: Response<List<WalkData>>) {
        if (response.isSuccessful && response.body() != null) {
            Log.d("WalkingPage", "postWalkData response: ${response.body()}")
        } else {
            val errorBody = response.errorBody()?.string() ?: "Unknown error"
            Log.e("WalkingPage", "postWalkData error: $errorBody")
            throw Exception("Error posting walk data: $errorBody")
        }
    }
    LaunchedEffect(viewModel) {
        try {
            Log.d("WalkingPage", "Posting walk data: $walkData")
            val response = viewModel.postWalkData(walkData)
            handleResponse(response)
            Log.d("WalkingPage", "Walk data posted successfully")
        } catch (e: Exception) {
            Log.e("WalkingPage", "Error posting walk data: ${e.message}")
            Log.d("WalkingPage", "Error posting walk data: ${e.message}")
        }
    }

    val steps = listOf(1000, 2000, 1500, 1800, 3500, 2700, 3200)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        item {
            LineGraph(steps = steps)
            Spacer(modifier = Modifier.height(16.dp))
            AchievementRate(
                percentage = 0.47f, navController
            )
            Spacer(modifier = Modifier.height(16.dp))
            WalkingType(todaySteps = todaySteps, highestSteps = highestSteps, totalSteps = totalSteps)
            WalkingContent()

        }
    }
}

