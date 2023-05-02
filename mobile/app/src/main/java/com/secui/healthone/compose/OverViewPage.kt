package com.secui.healthone.compose

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.secui.healthone.ui.overviewpage.*
import com.secui.healthone.util.FitAPIConfig
import com.secui.healthone.util.FitHeartManager
import com.secui.healthone.util.FitNutritionManager
import com.secui.healthone.util.FitSleepManager
import com.secui.healthone.util.FitWalkManager


lateinit var context: Context;
lateinit var thisActivity: Activity;

@Composable
fun OverViewPage(
    navController: NavHostController,
    modifier: Modifier = Modifier
        .fillMaxSize()
) {


    // 권한 요청
    context = LocalContext.current;
    thisActivity = LocalContext.current as Activity;
    FitAPIConfig.askFitAPIPermission(context = context, thisActivity = thisActivity)

    val walkValue = remember { // 걸음 값
        FitWalkManager.readWalkSteps(context, FitAPIConfig.getGoogleSignInAccount(context = context)) }

    val bpmValue = remember { // 심박 수
        FitHeartManager.readHeartPoint(context)
    }

    // 더미로 fit API로 값을 보냄
    FitSleepManager.writeSleepValue(context);
    val sleepValue = remember { FitSleepManager.readSleepValue(context) };

    val calorieValue = remember { FitNutritionManager.readNutritionData(context = context) } // 칼로리 값 , 미구현!

    Column(modifier= Modifier
        .fillMaxSize()
        .verticalScroll
            (rememberScrollState()))
    {
        TotalHealthBox(walkValue.value, sleepValue.value, calorieValue.value);
        UserWalkBox(navController, walkValue.value.toInt());
        HeartRateBox(navController, bpmValue.value.toInt());
        FoodCalorieBox(navController, calorieValue.value.toInt());
        SleepCheckBox(navController, sleepValue.value.toInt());
        HealthScoreBox(navController);
        Spacer(modifier = Modifier.height(64.dp));
    }
}
