package com.secui.healthone.reciever

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.secui.healthone.MainActivity
import com.secui.healthone.R
import com.secui.healthone.ui.alert.AlertItemText.Companion.alertContent
import com.secui.healthone.ui.alert.AlertItemText.Companion.alertType

class AlertReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notificationIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val alertType = intent.getStringExtra("alertType")
        val alertTime = intent.getStringExtra("alertTime")
        val alertContent = intent.getStringExtra("alertContent")

        val builder = NotificationCompat.Builder(context, "alertChannel")
            .setSmallIcon(R.drawable.ic_bell)
            .setContentTitle(alertType)
            .setContentText(alertContent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Channel name"
            val descriptionText = "Channel description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("alertChannel", name, importance).apply {
                description = descriptionText
            }

            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val localIntent = Intent("com.secui.healthone.ALERT_UPDATE").apply {
            putExtra("alertType", alertType)
            putExtra("alertTime", alertTime)
            putExtra("alertContent", alertContent)
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(localIntent)

        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(0, builder.build())
        }
    }
}