package com.secui.healthone

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.secui.healthone.service.ScreenService
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity: ComponentActivity() {

    override fun onResume() {
        super.onResume()
        // 화면 on/off 감지를 위한 Service 시작
        Log.i("SPLASH ::: ", "수면측정 기능 on...");
        val serviceIntent = Intent(this, ScreenService::class.java)
        startForegroundService(serviceIntent);
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash);

        lifecycleScope.launchWhenCreated {

            delay(1000)
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
