package com.secui.healthone.compose

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.secui.healthone.ui.overviewpage.FoodCalorieBox
import com.secui.healthone.ui.overviewpage.HealthScoreBox
import com.secui.healthone.ui.overviewpage.HeartRateBox
import com.secui.healthone.ui.overviewpage.SleepCheckBox
import com.secui.healthone.ui.overviewpage.TotalHealthBox
import com.secui.healthone.ui.overviewpage.UserWalkBox
import com.secui.healthone.api.fit.FitAPIConfig
import com.secui.healthone.api.fit.FitHeartManager
import com.secui.healthone.api.fit.FitNutritionManager
import com.secui.healthone.api.fit.FitSleepManager
import com.secui.healthone.api.fit.FitWalkManager


lateinit var context: Context;
lateinit var thisActivity: Activity;


@Composable
fun OverViewPage(
    navController: NavHostController,
    modifier: Modifier = Modifier
        .fillMaxSize()
) {

    // 초기값 세팅
    context = LocalContext.current;
    thisActivity = LocalContext.current as Activity;
    // pm 

    // 권한 요청
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

