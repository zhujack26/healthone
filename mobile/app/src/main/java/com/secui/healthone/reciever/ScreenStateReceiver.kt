package com.secui.healthone.reciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log


class ScreenStateReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_SCREEN_OFF) {
            // 화면이 꺼졌을 때 처리할 로직 작성
            Log.d("ScreenStateReceiver", "Screen off")
        } else if (intent?.action == Intent.ACTION_SCREEN_ON) {
            // 화면이 켜졌을 때 처리할 로직 작성
            Log.d("ScreenStateReceiver", "Screen on")
        }
    }



}