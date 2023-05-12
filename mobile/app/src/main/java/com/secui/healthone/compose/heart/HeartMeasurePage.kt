package com.secui.healthone.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.secui.healthone.ui.heart.heartmeasurepage.HeartMeasureItem
import com.secui.healthone.viewmodel.HeartRateViewModel


@Composable
fun HeartMeasurePage(
    navController: NavHostController,
    modifier: Modifier = Modifier
){

    Column(modifier= Modifier
        .fillMaxSize()
        .verticalScroll
            (rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        HeartMeasureItem(navController);
    }
}