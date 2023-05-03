package com.secui.healthone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PowerManager
import android.os.SystemClock
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.secui.healthone.compose.DataCollectFirstPage
import com.secui.healthone.compose.DataCollectSecondPage
import com.secui.healthone.compose.LoginPage
import com.secui.healthone.compose.OverViewPage
import com.secui.healthone.ui.common.TopBar
import com.secui.healthone.util.PageRoutes
import com.secui.healthone.compose.*


class MainActivity : ComponentActivity() {

    lateinit var pm: PowerManager;

    override fun onResume() {
        super.onResume()
        // serviceIntent = Intent(this, TrackerService::class.java)

        val time1 = SystemClock.elapsedRealtime()
        val time2 = SystemClock.uptimeMillis()
        Log.d("PM:::", "${(time1-time2)/1000}")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pm = this.getSystemService(Context.POWER_SERVICE) as PowerManager



        // 작업 등록
        // startAlarm()


        setContent {
            val sharedPreferences = getSharedPreferences("healthone", Context.MODE_PRIVATE)
            val jwtToken = sharedPreferences.getString("jwt_token", null)

            val navController = rememberNavController()

            NavHost(navController, startDestination = if (jwtToken != null) PageRoutes.Login.route else PageRoutes.OverView.route) {
                // 원래 코드는 jwtToken == null

                composable(PageRoutes.Login.route){
                    LoginPage(navController)
                }
                composable(PageRoutes.OverView.route) {
                    Column {
                        TopBar()
                        OverViewPage(navController)
                    }
                }
                composable(PageRoutes.DataCollectFirst.route){
                    DataCollectFirstPage(navController)
                }
                composable(PageRoutes.DataCollectSecond.route){
                    DataCollectSecondPage(navController)
                }
                composable(PageRoutes.Guide.route){
                    GuidePage(navController)
                }
            }
        }
    }


    // Activity가 화면에서 사라질 때 호출
    override fun onStop() {
        super.onStop()
        Log.d("PM:::", "OnStop...... ${pm.isInteractive}")

    }
}