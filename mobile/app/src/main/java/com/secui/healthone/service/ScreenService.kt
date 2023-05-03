package com.secui.healthone.service

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import com.secui.healthone.reciever.ScreenStateReceiver

class ScreenService : Service() {
    private lateinit var screenStateReceiver: ScreenStateReceiver;

    override fun onCreate() {
        super.onCreate()
        // BroadcastReceiver 등록
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        filter.addAction(Intent.ACTION_SCREEN_ON)
        screenStateReceiver = ScreenStateReceiver()
        registerReceiver(screenStateReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        // BroadcastReceiver 해제
        unregisterReceiver(screenStateReceiver)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}