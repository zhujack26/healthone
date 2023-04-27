package com.secui.healthone

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.secui.healthone.compose.*
import com.secui.healthone.compose.MealPlan.MealInputPage
import com.secui.healthone.ui.common.TopBar
import com.secui.healthone.util.PageRoutes


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sharedPreferences = getSharedPreferences("healthone", Context.MODE_PRIVATE)
            val jwtToken = sharedPreferences.getString("jwt_token", null)

            val navController = rememberNavController()

            NavHost(navController, startDestination = if (jwtToken == null) "login" else "overviewpage") {
                // 원래 코드는 jwtToken == null
                composable("login") {
                    LoginPage(navController)
                }
                composable("overviewpage") {
                    Column {
                        TopBar()
                        OverViewPage(navController)
                    }
                }
                composable("datacollect1") { DataCollectFirstPage(navController) }
                composable("datacollect2") { DataCollectSecondPage() }
            }
        }
    }
}