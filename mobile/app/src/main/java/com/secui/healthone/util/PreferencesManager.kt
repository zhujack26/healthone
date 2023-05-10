package com.secui.healthone.util

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("HealthOne", Context.MODE_PRIVATE)

    fun setSleepTime(sleepTime: String) {
        sharedPreferences.edit().putString("SLEEP_TIME", sleepTime).apply()
    }

    fun getSleepTime(): String {
        return sharedPreferences.getString("SLEEP_TIME", "") ?: ""
    }

    fun setWakeTime(wakeTime: String) {
        sharedPreferences.edit().putString("WAKE_TIME", wakeTime).apply()
    }

    fun getWakeTime(): String {
        return sharedPreferences.getString("WAKE_TIME", "") ?: ""
    }

    fun setSleepDuration(sleepDuration: String) {
        sharedPreferences.edit().putString("SLEEP_DURATION", sleepDuration).apply()
    }

    fun getSleepDuration(): String {
        return sharedPreferences.getString("SLEEP_DURATION", "") ?: ""
    }
}