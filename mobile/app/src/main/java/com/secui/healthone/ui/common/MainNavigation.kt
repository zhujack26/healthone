package com.secui.healthone.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.secui.healthone.compose.AlarmSettingPage
import com.secui.healthone.compose.AlertPage
import com.secui.healthone.compose.challenge.PopularDetailPage
import com.secui.healthone.compose.ChallengePage
import com.secui.healthone.compose.challenge.*
import com.secui.healthone.compose.HeartMeasurePage
import com.secui.healthone.compose.HeartRatePage
import com.secui.healthone.compose.MealPlan.ExerciseInputPage
import com.secui.healthone.compose.MealPlan.MealInputPage
import com.secui.healthone.compose.MealPlanPage
import com.secui.healthone.compose.MyPage
import com.secui.healthone.compose.OverViewPage
import com.secui.healthone.compose.SettingPage
import com.secui.healthone.compose.WalkingDetailPage
import com.secui.healthone.compose.WalkingPage
import com.secui.healthone.compose.healthstatus.HealthHelpPage
import com.secui.healthone.compose.healthstatus.HealthInputPage
import com.secui.healthone.compose.healthstatus.HealthStatusPage
import com.secui.healthone.compose.sleep.SleepPage
import com.secui.healthone.util.PageRoutes

@Composable
fun TopBarNavigation(navController: NavHostController) {
    Column {
        NavHost(navController, startDestination = PageRoutes.OverView.route) {
            composable(PageRoutes.OverView.route) {
                OverViewPage(navController = navController)
            }
            composable(PageRoutes.MealPlan.route) {
                MealPlanPage(navController = navController) // 식단 화면
            }
            composable(PageRoutes.HeartRate.route) {
                HeartRatePage(navController = navController) // 심박수 측정
            }
            composable(PageRoutes.Challenge.route) {
                ChallengePage(navController = navController) // 챌린지
            }
            composable(PageRoutes.Alert.route) {
                AlertPage(navController = navController) /// 알림
            }
            composable(PageRoutes.HeartMeasure.route) {
                HeartMeasurePage(navController)
            }
            composable(PageRoutes.MealInput.route) {
                MealInputPage(navController)
            }
            composable(PageRoutes.ExerciseInput.route) {
                ExerciseInputPage(navController)
            }
            composable(PageRoutes.Sleep.route) {
                SleepPage(navController, sleepRecords = mutableListOf())
            }
            composable(PageRoutes.My.route) {
                MyPage(navController = navController)
            }
            composable(PageRoutes.Walking.route) {
                WalkingPage(navController)
            }
            composable(PageRoutes.WalkingDetail.route) {
                WalkingDetailPage(navController)
            }
            composable(PageRoutes.PopularDetail.route) {
                PopularDetailPage(navController)
            }
            composable(PageRoutes.Setting.route) {
                SettingPage(navController = navController)
            }
            composable(PageRoutes.AlarmSetting.route) {
                AlarmSettingPage()
            }
            composable(PageRoutes.HealthStatus.route){
                HealthStatusPage(navController = navController)
            }
            composable(PageRoutes.HealthStatusInput.route){
                HealthInputPage(navController = navController)
            }
            composable(PageRoutes.HealthHelp.route){
                HealthHelpPage(navController = navController)
            }
        }
    }
}