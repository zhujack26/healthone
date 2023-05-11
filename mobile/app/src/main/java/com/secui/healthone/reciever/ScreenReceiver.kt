package com.secui.healthone.reciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.secui.healthone.util.PreferenceUtil
import java.time.LocalDateTime

class ScreenReceiver : BroadcastReceiver() {

    lateinit var prefs:PreferenceUtil

    override fun onReceive(context: Context?, intent: Intent?) {

        prefs = PreferenceUtil(context!!); // context가 null이 오진 않는다.
        val nowTime = LocalDateTime.now();

        if (intent?.action == Intent.ACTION_SCREEN_ON) {
            // 스크린 ON
            Log.i("SCREEN", "스크린 켜짐 (ON) >> TIME : ${nowTime.toString()}")
            prefs.setString("wake_time", "${nowTime.toString()}")
            // to 생각... 만약 사용자가 낮에 4시간 이상 폰을 안키면?
            // 현재 - 취침이 4시간 이상이면 유효한 취침이라 판단하고 수면시간을 저장

        } else if (intent?.action == Intent.ACTION_SCREEN_OFF) {
            // 스크린 OFF
            Log.i("SCREEN", "스크린 꺼짐 (OFF) >> TIME : ${nowTime.toString()}")
            prefs.setString("sleep_time", "${nowTime.toString()}")
        }
    }
}