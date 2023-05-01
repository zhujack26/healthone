package com.secui.healthone.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.secui.healthone.ui.datacollectpage.*
import com.secui.healthone.ui.mypage.*

@Composable
fun MyPage(navController: NavController) {
    val totalSteps = "10000" // 백엔드에서 받아올 데이터
    val totalTime = "150분"
    val totalCalories = "700kcal"
    val bestSteps = "12000"
    val bestTime = "160분"
    val bestCalories = "800kcal"
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        item {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                PhotoPicker()
                Text(
                    text = "닉네임",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(32.dp))
                WeeklyAnalysis()
                Spacer(modifier = Modifier.height(32.dp))
                Records(totalSteps, totalTime, totalCalories, bestSteps, bestTime, bestCalories)
            }

        }
    }
}