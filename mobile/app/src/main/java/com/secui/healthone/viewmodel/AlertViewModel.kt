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
import com.secui.healthone.R

class AlertViewModel(private val context: Context, private val prefs: PreferencesManager) : ViewModel() {
    private val _wakeAlert = MutableLiveData<AlertItemText>()
    val wakeAlert: LiveData<AlertItemText> get() = _wakeAlert

    private val _sleepAlert = MutableLiveData<AlertItemText>()
    val sleepAlert: LiveData<AlertItemText> get() = _sleepAlert

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val alertType = intent.getStringExtra("alertType")
            val alertTime = intent.getStringExtra("alertTime")
            val alertContent = intent.getStringExtra("alertContent")
            val alertImage = intent.getStringExtra("alertImage")
            val alertItem = AlertItemText(alertType ?: "", alertTime ?: "", alertContent ?: "", alertImage ?: "")

            if (alertType == "기상") {
                _wakeAlert.postValue(alertItem)
            } else if (alertType == "취침") {
                _sleepAlert.postValue(alertItem)
            }
        }
    }

    fun setAlert() {
        val wakeTime = prefs.getWakeTime()
        if (wakeTime.isBlank()) {
            Log.d("AlertViewModel", "Wake time is empty")
            return
        }
        val timeInMillis = parseTimeToMillis(wakeTime)
        val image = R.drawable.ic_speaker
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlertReceiver::class.java).apply {
            putExtra("alertType", "기상")
            putExtra("alertTime", wakeTime)
            putExtra("alertContent", "하루가 시작되었네요. 활기차게 움직여볼까요?")
            putExtra("alertImage", image)

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
    fun setSleepAlert() {
        val sleepTime = prefs.getSleepTime()
        if (sleepTime.isBlank()) {
            Log.d("AlertViewModel", "Sleep time is empty")
            return
        }
        val timeInMillis = parseTimeToMillis(sleepTime)
        val image = R.drawable.ic_speaker
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlertReceiver::class.java).apply {
            putExtra("alertType", "취침")
            putExtra("alertTime", sleepTime)
            putExtra("alertContent", "이제 주무실 시간이에요")
            putExtra("alertImage", image)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            1,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )
        Log.d("AlertViewModel", "Sleep Alarm set at $timeInMillis")
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
        return if (time.isBlank()) {
            Log.d("AlertViewModel", "Time string is blank")
            0L
        } else {
            val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
            val date = sdf.parse(time)
            date?.time ?: 0L
        }
    }


    data class AlertItemText(
        val alertType: String,
        val alertTime: String,
        val alertContent: String,
        val alertImage: String
    )
}