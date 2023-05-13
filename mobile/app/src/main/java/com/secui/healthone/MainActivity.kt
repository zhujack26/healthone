package com.secui.healthone

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.secui.healthone.compose.OverViewPage
import com.secui.healthone.compose.signup.DataCollectFirstPage
import com.secui.healthone.compose.signup.DataCollectSecondPage
import com.secui.healthone.compose.signup.GuidePage
import com.secui.healthone.compose.signup.LoginPage
import com.secui.healthone.service.ScreenService
import com.secui.healthone.ui.common.TopBar
import com.secui.healthone.constant.PageRoutes
import com.secui.healthone.util.PreferenceUtil
import okhttp3.Cookie


class MainActivity : ComponentActivity() {

    lateinit var prefs:PreferenceUtil;
    override fun onResume() {
        super.onResume()
        prefs = PreferenceUtil(this);
    }

    private fun hasRefreshToken(sharedPreferences: SharedPreferences): Boolean {
        val cookiesJsonSet = sharedPreferences.getStringSet("cookies", null)
        cookiesJsonSet?.let {
            it.forEach { json ->
                val cookie = Gson().fromJson(json, Cookie::class.java)
                if (cookie.name == "refreshtoken") {
                    return true
                }
            }
        }
        return false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val addOnSuccessListener = FirebaseMessaging.getInstance().token.addOnSuccessListener {
//            Log.d("TOKEN", it)
//        }

        setContent {
            val sharedPreferences = getSharedPreferences("healthone", Context.MODE_PRIVATE)
            val navController = rememberNavController()
            val mOwner = LocalLifecycleOwner.current

            val allEntries: Map<String, *> = sharedPreferences.getAll()  //sharedPreferences 확인
            for ((key, value) in allEntries) {
                Log.d("SharedPreferences", key + ": " + value.toString())
            }

            NavHost(navController, startDestination = if (!hasRefreshToken(sharedPreferences)) PageRoutes.Login.route else PageRoutes.OverView.route) {
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
        // 화면 on/off 감지를 위한 Service 시작
        Log.i("SPLASH ::: ", "수면측정 기능 on...");
        val serviceIntent = Intent(this, ScreenService::class.java)
        startForegroundService(serviceIntent);
    }

    // Activity가 화면에서 사라질 때 호출
    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}