package com.secui.healthone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.messaging.FirebaseMessaging
import com.secui.healthone.compose.OverViewPage
import com.secui.healthone.compose.signup.DataCollectFirstPage
import com.secui.healthone.compose.signup.DataCollectSecondPage
import com.secui.healthone.compose.signup.GuidePage
import com.secui.healthone.compose.signup.LoginPage
import com.secui.healthone.service.ScreenService
import com.secui.healthone.ui.common.TopBar
import com.secui.healthone.util.PageRoutes
import com.secui.healthone.util.PreferenceUtil

class MainActivity : ComponentActivity() {
    lateinit var prefs:PreferenceUtil;
    override fun onResume() {
        super.onResume()
        prefs = PreferenceUtil(this);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            Log.d("TOKEN", it)
        }

        setContent {
            val sharedPreferences = getSharedPreferences("healthone", Context.MODE_PRIVATE)
            val jwtToken = sharedPreferences.getString("jwt_token", null)
            val navController = rememberNavController()
            val mOwner = LocalLifecycleOwner.current

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
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    // Activity가 화면에서 사라질 때 호출
    override fun onStop() {
        super.onStop()
        // 화면 on/off 감지를 위한 Service 시작
        val serviceIntent = Intent(this, ScreenService::class.java)
        startForegroundService(serviceIntent);
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}