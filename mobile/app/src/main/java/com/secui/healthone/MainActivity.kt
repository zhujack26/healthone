package com.secui.healthone

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
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
import com.secui.healthone.viewmodel.HealthInfoViewModel
import com.secui.healthone.viewmodel.UserViewModel
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

        setContent {
            val userViewModel: UserViewModel = viewModel()
            val sharedPreferences = getSharedPreferences("healthone", Context.MODE_PRIVATE)
            val navController = rememberNavController()
            val mOwner = LocalLifecycleOwner.current
            val context = this
            val allEntries: Map<String, *> = sharedPreferences.getAll()  //sharedPreferences 확인
            for ((key, value) in allEntries) {
                Log.d("SharedPreferences", key + ": " + value.toString())
            }
            HandleBackPressExample(navController, this)

            NavHost(navController, startDestination = if (!hasRefreshToken(sharedPreferences)) PageRoutes.Login.route else PageRoutes.OverView.route) {
                composable(PageRoutes.Login.route) {
                    LoginPage(navController)
                }
                composable(PageRoutes.OverView.route) {
                    Column {
                        TopBar(context)
                        OverViewPage(navController)
                    }
                }
                composable(PageRoutes.DataCollectFirst.route) {
                    DataCollectFirstPage(navController, userViewModel)
                }
                composable(PageRoutes.DataCollectSecond.route) {
                    DataCollectSecondPage(navController, userViewModel)
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

//백스텍 제어 및 토스트 문구
@Composable
fun HandleBackPressExample(navController: NavController, context: Context) {
    var backPressedTime = remember { mutableStateOf(0L) }
    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (navController.currentDestination?.route == PageRoutes.OverView.route) {
                    val currentTime = System.currentTimeMillis()
                    if (currentTime - backPressedTime.value > 2000) {
                        //두 번 눌린 시간 사이의 차이가 2초보다 작으면 앱을 종료하고, 그렇지 않으면 토스트 메시지
                        backPressedTime.value = currentTime
                        Toast.makeText(context, "한번 더 누르면 앱이 종료됩니다", Toast.LENGTH_SHORT).show()
                    } else {
                        (context as Activity).finish()
                    }
                } else {
                    navController.popBackStack()
                }
            }
        }
    }

    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    DisposableEffect(key1 = backDispatcher) {
        backDispatcher?.addCallback(backCallback)
        onDispose {
            backCallback.remove()
        }
    }
}






