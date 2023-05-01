package com.secui.healthone.compose

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.secui.healthone.ui.common.TopBar
import com.secui.healthone.ui.overviewpage.*
import com.secui.healthone.util.PageRoutes


@Composable
fun OverViewPage(
    navController: NavHostController,
    modifier: Modifier = Modifier
        .fillMaxSize()
) {

    val context = LocalContext.current;
    val account = GoogleSignIn.getLastSignedInAccount(context)
    if (account == null) {
        // 사용자가 로그인하지 않았으면 로그인 화면으로 이동
        // ...

        navController.navigate(PageRoutes.Login.route)

    } else {
        // 사용자가 로그인한 경우
        // API 클라이언트 생성
        val historyClient = Fitness.getHistoryClient(context, account)
         // navController.navigate(PageRoutes.OverView.route)
        Log.d("overview:::::", "${historyClient.toString()}");
    }

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

