package com.secui.healthone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.secui.healthone.compose.MealPlanPage
import com.secui.healthone.compose.LoginPage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "main") {
                composable("main") {
                    MainPage(navController = navController)
                }
                composable("mealPlanPage") {
                    MealPlanPage()
                }
                composable("login") {
                    LoginPage()
                }
            }
        }
    }
}

@Composable
fun MainPage(navController: NavController) {
    Column {
        Button(onClick = { navController.navigate("mealPlanPage") }) {
            Text("Go to Meal Plan Page")
        }
        Button(onClick = { navController.navigate("login") }) {
            Text("Go to Login Page")
        }
    }
}