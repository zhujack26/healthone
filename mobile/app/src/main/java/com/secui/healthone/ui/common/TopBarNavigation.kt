package com.secui.healthone.ui.common

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.secui.healthone.compose.setting.AlarmSettingPage
import com.secui.healthone.compose.AlertPage
import com.secui.healthone.compose.challenge.PopularDetailPage
import com.secui.healthone.compose.ChallengePage
import com.secui.healthone.compose.HeartMeasurePage
import com.secui.healthone.compose.HeartRatePage
import com.secui.healthone.compose.MealPlan.ExerciseInputPage
import com.secui.healthone.compose.MealPlan.MealInputPage
import com.secui.healthone.compose.MealPlanPage
import com.secui.healthone.compose.MyPage
import com.secui.healthone.compose.OverViewPage
import com.secui.healthone.compose.setting.UserInformDeletePage
import com.secui.healthone.compose.setting.UserInformDownPage
import com.secui.healthone.compose.setting.SettingPage
import com.secui.healthone.compose.walking.WalkingDetailPage
import com.secui.healthone.compose.walking.WalkingPage
import com.secui.healthone.compose.healthstatus.HealthHelpPage
import com.secui.healthone.compose.healthstatus.HealthInputPage
import com.secui.healthone.compose.healthstatus.HealthStatusPage
import com.secui.healthone.compose.signup.DataCollectFirstPage
import com.secui.healthone.compose.signup.DataCollectSecondPage
import com.secui.healthone.compose.sleep.SleepPage
import com.secui.healthone.constant.PageRoutes
import com.secui.healthone.viewmodel.HealthInfoViewModel
import java.time.LocalDate

@Composable
fun TopBarNavigation(navController: NavHostController, context: Context) {
    val account = GoogleSignIn.getLastSignedInAccount(context)

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
                SleepPage(navController)
            }
            composable(PageRoutes.My.route) {
                MyPage(navController = navController)
            }
            composable(PageRoutes.Walking.route) {
                WalkingPage(navController)
            }
            composable("walkingDetail/{date}", arguments = listOf(navArgument("date") { type = NavType.StringType })) { backStackEntry ->
                val date = LocalDate.parse(backStackEntry.arguments?.getString("date"))
                account?.let {
                    WalkingDetailPage(navController = navController, context = context, account = it, date = date)
                }
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
            composable(PageRoutes.UserInformDelete.route){
                UserInformDeletePage()
            }
            composable(PageRoutes.UserInformDown.route){
                UserInformDownPage()
            }
            composable(PageRoutes.DataCollectFirst.route) {
                DataCollectFirstPage(navController)
            }
            composable(PageRoutes.DataCollectSecond.route) {
                DataCollectSecondPage(navController)
            }
        }
    }
}