package com.secui.healthone.compose

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import com.secui.healthone.compose.factory.YouTubeViewModelFactory
import com.secui.healthone.data.ApiResponse
import com.secui.healthone.service.YouTubeService
import com.secui.healthone.viewmodel.ContentViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun WalkingPage(
    navController: NavController,
){
    val context = LocalContext.current
    val account = FitAPIConfig.getGoogleSignInAccount(context = context)

    // 오늘 걸음수
    val walkValue: State<Int> = remember {
        FitWalkManager.readWalkSteps(context, account)
    }
    val todaySteps = walkValue.value

    // 걸은 거리
    val distanceValue: State<Float> = remember {
        FitWalkManager.readDistanceData(context, account)
    }
    val todayDistance = distanceValue.value

    // 최고 걸음수
    val highestStepsValue: State<Int> = remember {
        FitWalkManager.readMaxDailySteps(context, account)
    }
    val highestSteps = highestStepsValue.value

    // 총 걸음수
    val totalStepsValue: State<Int> = remember {
        FitWalkManager.readTotalSteps(context, account)
    }
    val totalSteps = totalStepsValue.value

    // 걸음 목표 달성률
    val stepGoal = 6000f // 목표 걸음수 설정
    val achievementRate = if (todaySteps > stepGoal) 1f else todaySteps / stepGoal // 달성률 계산


    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/youtube/v3/") // YouTube API base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val youtubeService = retrofit.create(YouTubeService::class.java)
    val factory = YouTubeViewModelFactory(youtubeService)

    val contentViewModel: ContentViewModel = viewModel(factory = factory)
    val viewModel: WalkViewModel = viewModel()
    val walkDataListState = remember { mutableStateOf<List<Int>>(emptyList()) }

    LaunchedEffect(Unit) {
        walkDataListState.value = viewModel.getPastWeekWalkData().value
    }
    val videos = contentViewModel.videos.value
    LaunchedEffect(Unit) {
        contentViewModel.searchVideos()
    }
    val steps = walkDataListState.value + todaySteps

    val walkData = WalkData(
        userNo = 1, // 실제 사용자 번호로
        stepCount = todaySteps,
        distance = todayDistance.toDouble(), // 구글 Fit API에서 distance 값을 가져오거나 빈 값 사용
    )
    fun handleResponse(response: Response<ApiResponse<List<WalkData>>>) {
        if (response.isSuccessful && response.body() != null) {
            val apiResponse = response.body()!!
            Log.d("WalkingPage", "postWalkData response: ${apiResponse.data}")
        } else {
            val errorBody = response.errorBody()?.string() ?: "Unknown error"
            Log.e("WalkingPage", "postWalkData error: $errorBody")
            throw Exception("Error posting walk data: $errorBody")
        }
    }
    LaunchedEffect(Unit) {
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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        item {
            LineGraph(steps = steps)
            Spacer(modifier = Modifier.height(16.dp))
            AchievementRate(
                percentage = achievementRate, navController
            )
            Spacer(modifier = Modifier.height(16.dp))
            WalkingType(todaySteps = todaySteps, highestSteps = highestSteps, totalSteps = totalSteps)
            Spacer(modifier = Modifier.height(16.dp))
            WalkingContent(videos = videos)

        }
    }
}