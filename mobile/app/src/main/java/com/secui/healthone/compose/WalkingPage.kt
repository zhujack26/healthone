package com.secui.healthone.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.secui.healthone.ui.walking.*

@Composable
fun WalkingPage(
    navController: NavHostController,
    modifier: Modifier=Modifier){
    LineGraph()
    Spacer(modifier = Modifier.height(16.dp))
    AchievementRate()
}