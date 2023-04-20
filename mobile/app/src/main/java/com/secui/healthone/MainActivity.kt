package com.secui.healthone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.secui.healthone.compose.MealPlanPage

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
            }
        }
    }
}

@Composable
fun MainPage(navController: NavController) {
    Button(onClick = { navController.navigate("mealPlanPage") }) {
        Text("Go to Meal Plan Page")
    }
}
