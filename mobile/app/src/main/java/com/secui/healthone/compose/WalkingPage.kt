package com.secui.healthone.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.secui.healthone.ui.walking.*

@Composable
fun WalkingPage(
    navController: NavHostController,
    todaySteps: Int = 1000,
    highestSteps: Int = 5000,
    totalSteps: Int = 10000){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        item {
            LineGraph()
            Spacer(modifier = Modifier.height(16.dp))
            AchievementRate(
                percentage = 0.47f,
            )
            Spacer(modifier = Modifier.height(16.dp))
            WalkingType(todaySteps = todaySteps, highestSteps = highestSteps, totalSteps = totalSteps)
            WalkingContent()
            WalkingDetail()
        }
    }
}