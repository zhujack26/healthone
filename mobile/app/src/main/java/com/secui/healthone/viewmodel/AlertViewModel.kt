package com.secui.healthone.viewmodel

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.secui.healthone.reciever.AlertReceiver
import com.secui.healthone.util.PreferencesManager
import java.text.SimpleDateFormat
import java.util.Locale
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.AlarmManagerCompat
import androidx.core.content.ContextCompat

class AlertViewModel(private val context: Context, private val prefs: PreferencesManager) : ViewModel() {
    private val _alert = MutableLiveData<AlertItemText>()
    val alert: LiveData<AlertItemText> get() = _alert

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val alertType = intent.getStringExtra("alertType")
            val alertTime = intent.getStringExtra("alertTime")
            val alertContent = intent.getStringExtra("alertContent")

            _alert.postValue(AlertItemText(alertType ?: "", alertTime ?: "", alertContent ?: ""))
        }
    }

    fun setAlert() {
        val wakeTime = prefs.getWakeTime()
        val timeInMillis = parseTimeToMillis(wakeTime)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlertReceiver::class.java).apply {
            putExtra("alertType", "기상")
            putExtra("alertTime", wakeTime)
            putExtra("alertContent", "오늘 하루 활기차게 보내기 위해 한번 뛰어볼까요?")
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )
        Log.d("AlertViewModel", "Alarm set at $timeInMillis")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!alarmManager.canScheduleExactAlarms()) {
                val intent = Intent("android.app.action.ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_REQUEST_ACTION").apply {
                    data = Uri.fromParts("package", context.packageName, null)
                }
                context.startActivity(intent)
            }
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
    }

    init {
        LocalBroadcastManager.getInstance(context)
            .registerReceiver(receiver, IntentFilter("com.secui.healthone.ALERT_UPDATE"))
    }

    override fun onCleared() {
        super.onCleared()
        LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver)
    }

    private fun parseTimeToMillis(time: String): Long {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val date = sdf.parse(time)
        return date?.time ?: 0L
    }


    data class AlertItemText(
        val alertType: String,
        val alertTime: String,
        val alertContent: String
    )
}