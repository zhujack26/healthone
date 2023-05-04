package com.secui.healthone

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.secui.healthone.compose.signup.*
import com.secui.healthone.compose.OverViewPage
import com.secui.healthone.ui.common.TopBar
import com.secui.healthone.util.PageRoutes
import com.secui.healthone.ui.webview.WebViewScreen


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sharedPreferences = getSharedPreferences("healthone", Context.MODE_PRIVATE)
            val jwtToken = sharedPreferences.getString("jwt_token", null)
            val navController = rememberNavController()
            val mOwner = LocalLifecycleOwner.current

//            val repository = HealthOneRepository();
//            val viewModel = HealthOneViewModel(repository);
//
//            viewModel.getFoodInfo(1);
//            viewModel.foodResponse.observe(mOwner, Observer{
//
//            })

            NavHost(navController, startDestination = if (jwtToken == null) PageRoutes.Login.route else PageRoutes.OverView.route) {

                composable(PageRoutes.Login.route) {
                    LoginPage(navController)
                }
                composable(PageRoutes.OverView.route) {
                    Column {
                        TopBar()
                        OverViewPage(navController)
                    }
                }
                composable(PageRoutes.DataCollectFirst.route) {
                    DataCollectFirstPage(navController)
                }
                composable(PageRoutes.DataCollectSecond.route) {
                    DataCollectSecondPage(navController)
                }
                composable(PageRoutes.Guide.route) {
                    GuidePage(navController)
                }
                composable("webView") { backStackEntry ->
                    val url = backStackEntry.arguments?.getString("url")
                    WebViewScreen(url = url ?: "", onBack = { navController.popBackStack() })
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // startService(Intent(this, ScreenService::class.java))
    }

    // Activity가 화면에서 사라질 때 호출
    override fun onStop() {
        super.onStop()
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}