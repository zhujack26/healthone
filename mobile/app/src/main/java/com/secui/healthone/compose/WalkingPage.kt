package com.secui.healthone.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.secui.healthone.ui.walking.LineGraph

@Composable
fun WalkingPage(
    navController: NavHostController,
    modifier: Modifier=Modifier){
    LineGraph()
}