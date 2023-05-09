package com.secui.healthone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.secui.healthone.compose.signup.*
import com.secui.healthone.compose.OverViewPage
import com.secui.healthone.ui.common.TopBar
import com.secui.healthone.util.PageRoutes
import com.secui.healthone.compose.*
import com.secui.healthone.service.MyService
import com.secui.healthone.util.PreferenceUtil


class MainActivity : ComponentActivity() {

    lateinit var prefs:PreferenceUtil;

    override fun onResume() {
        super.onResume()
        prefs = PreferenceUtil(this);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val sharedPreferences = getSharedPreferences("healthone", Context.MODE_PRIVATE)
            val jwtToken = sharedPreferences.getString("jwt_token", null)
            val navController = rememberNavController()
            val mOwner = LocalLifecycleOwner.current

            NavHost(navController, startDestination = if (jwtToken != null) PageRoutes.Login.route else PageRoutes.OverView.route) {

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
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    // Activity가 화면에서 사라질 때 호출
    override fun onStop() {
        super.onStop()
        // SystemClock.elapsedRealtime();
        // 서비스 시작
//        val serviceIntent = Intent(this, MyService::class.java)
//        // ContextCompat.startForegroundService(this, serviceIntent)
//        startService(serviceIntent);
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}