package com.secui.healthone.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.secui.healthone.ui.overview.*


@Composable
fun OverViewPage(
    modifier: Modifier = Modifier
        .fillMaxSize()
) {
    val size = 8;
    Column(modifier= Modifier
        .fillMaxSize()
        .verticalScroll
            (rememberScrollState()))
    {

        TotalHealthBox();
        UserWalkBox();
        HeartRateBox();
        StressIndexBox();
        FoodCalorieBox();
        SleepCheckBox();
        HealthScoreBox();
        Spacer(modifier = Modifier.height(64.dp));

    }
}

