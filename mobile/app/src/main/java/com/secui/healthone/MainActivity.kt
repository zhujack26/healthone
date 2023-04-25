package com.secui.healthone

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.secui.healthone.compose.DataCollectPage
import com.secui.healthone.compose.LoginPage
import com.secui.healthone.compose.OverViewPage
import com.secui.healthone.ui.common.TopBar


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sharedPreferences = getSharedPreferences("healthone", Context.MODE_PRIVATE)
            val jwtToken = sharedPreferences.getString("jwt_token", null)

            val navController = rememberNavController()

            NavHost(navController, startDestination = if (jwtToken == null) "login" else "overviewpage") {
                composable("login") {
                    LoginPage(navController)
                }
                composable("overviewpage") {
                    Column {
                        TopBar()
                        OverViewPage()
                    }
                }
                composable("DataCollectPage") {
                    DataCollectPage()
                }
            }
        }
    }
}