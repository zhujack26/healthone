package com.secui.healthone.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import com.secui.healthone.R
import com.secui.healthone.compose.MealPlanPage
import com.secui.healthone.compose.OverViewPage
import com.secui.healthone.compose.*
import com.secui.healthone.compose.Challenge.PopularDetailPage
import com.secui.healthone.compose.MealPlan.ExerciseInputPage
import com.secui.healthone.compose.MealPlan.MealInputPage
import com.secui.healthone.compose.Stress.StressBreathPage
import com.secui.healthone.compose.sleep.SleepPage
import com.secui.healthone.util.PageRoutes


@Preview
@Composable
fun TopBar() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    // BeatRoutes.Home.route

//    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl ) {
//    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Icon(painter = painterResource(id = R.drawable.ic_topbar_logo),
                    contentDescription = "logo",
                    tint = colorResource(id = R.color.black),
                    modifier = Modifier.size(60.dp)
                ) },
                backgroundColor = colorResource(id = R.color.white),
                actions = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(painter = painterResource(id = R.drawable.ic_topbar_toggle),
                            contentDescription = "Menu",
                            tint = colorResource(id = R.color.black),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        },
        drawerContent = {
            Column (){
                DrawerButton(
                    text = "내 페이지",
                    textColor = R.color.black,
                    onClick = {
                        navController.navigate(PageRoutes.My.route)
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                )
                DrawerButton(
                    text = "메인",
                    textColor = R.color.black,
                    onClick = {
                        navController.navigate(PageRoutes.OverView.route)
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                )
                DrawerButton(
                    text = "심박수",
                    textColor = R.color.black,
                    onClick = {
                        navController.navigate(PageRoutes.HeartRate.route)
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                )
                DrawerButton(
                    text = "식단",
                    textColor = R.color.black,
                    onClick = {
                        navController.navigate(PageRoutes.MealPlan.route)
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                )
                DrawerButton(
                    text = "수면",
                    textColor = R.color.black,
                    onClick = {
                        navController.navigate(PageRoutes.Sleep.route)
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                )
                DrawerButton(
                    text = "걸음수",
                    textColor = R.color.black,
                    onClick = {
                        navController.navigate(PageRoutes.Walking.route)
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                )
                DrawerButton(
                    text = "스트레스 측정",
                    textColor = R.color.black,
                    onClick = {
                        navController.navigate(PageRoutes.StressIndex.route)
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                )
                DrawerButton(
                    text = "심박 수 측정",
                    textColor = R.color.black,
                    onClick = {
                        navController.navigate(PageRoutes.HeartRate.route)
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                )

                DrawerButton(
                    text = "챌린지 페이지",
                    textColor = R.color.black,
                    onClick = {
                        navController.navigate(PageRoutes.Challenge.route)
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                )
                DrawerButton(
                    text = "알림 페이지",
                    textColor = R.color.black,
                    onClick = {
                        navController.navigate(PageRoutes.Alert.route)
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                )
                DrawerButton(
                    text = "설정",
                    textColor = R.color.black,
                    onClick = {
                        navController.navigate(PageRoutes.Setting.route)
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                )
            }
        },
        content = {
                padding ->
            Column(
                modifier = Modifier
                    .padding(padding)){
                NavHost(navController, startDestination = PageRoutes.OverView.route) {
                    composable(PageRoutes.OverView.route) {
                        OverViewPage(navController = navController)
                    }
                    composable(PageRoutes.MealPlan.route) {
                        MealPlanPage(navController = navController) // 식단 화면
                    }
                    composable(PageRoutes.StressIndex.route) {
                        StressIndexPage(navController = navController) // 스트레스 관리
                    }
                    composable(PageRoutes.HeartRate.route){
                        HeartRatePage(navController = navController) // 심박수 측정
                    }
                    composable(PageRoutes.Challenge.route){
                        ChallengePage(navController = navController) // 챌린지
                    }
                    composable(PageRoutes.Alert.route){
                        AlertPage(navController = navController) /// 알림
                    }
                    composable(PageRoutes.StressBreath.route){
                        StressBreathPage(navController = navController)
                    }
                    composable(PageRoutes.StressActivity.route){
                        StressActivityPage(navController = navController);
                    }
                    composable(PageRoutes.HeartMeasure.route){
                        HeartMeasurePage(navController);
                    }
                    composable(PageRoutes.MealInput.route){
                        MealInputPage(navController);
                    }
                    composable(PageRoutes.ExerciseInput.route){
                        ExerciseInputPage(navController);
                    }
                    composable(PageRoutes.Sleep.route){
                        SleepPage(navController = navController)
                    }
                    composable(PageRoutes.My.route){
                        MyPage(navController = navController);
                    }
                    composable(PageRoutes.Walking.route){
                        WalkingPage(navController = navController);
                    }
                    composable(PageRoutes.PopularDetail.route){
                        PopularDetailPage(navController)
                    }
                    composable(PageRoutes.Setting.route){
                        SettingPage(navController = navController)
                    }
                }
            }
        }
    )
}



@Composable
fun DrawerButton(text: String, textColor: Int = R.color.black, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text, fontSize = 16.sp, color = colorResource(id = textColor))
    }
}