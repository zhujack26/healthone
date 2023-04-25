package com.secui.healthone.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.secui.healthone.ui.overviewpage.*


@Composable
fun OverViewPage(
    navController: NavHostController,
    modifier: Modifier = Modifier
        .fillMaxSize()
) {
    Column(modifier= Modifier
        .fillMaxSize()
        .verticalScroll
            (rememberScrollState()))
    {

        TotalHealthBox();
        UserWalkBox(navController);
        HeartRateBox(navController);
        StressIndexBox(navController);
        FoodCalorieBox(navController);
        SleepCheckBox(navController);
        HealthScoreBox(navController);
        Spacer(modifier = Modifier.height(64.dp));

    }
}

