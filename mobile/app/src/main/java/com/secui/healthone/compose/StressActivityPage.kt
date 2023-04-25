package com.secui.healthone.compose

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun StressActivityPage(
    navController: NavHostController,
    modifier: Modifier=Modifier){

    Text(text = "상세활동 페이지 입니당.");

}