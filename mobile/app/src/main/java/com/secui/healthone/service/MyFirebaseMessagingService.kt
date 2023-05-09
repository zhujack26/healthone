package com.secui.healthone.service

import android.content.Context
import android.os.PowerManager
import android.util.Log
import androidx.core.content.getSystemService
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
lateinit var pm:PowerManager


class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onCreate() {
        super.onCreate()
        Log.i("MESSAGE", "생성되었디...")
        pm = getSystemService(Context.POWER_SERVICE) as PowerManager
        Log.i("MESSAGE!", "${pm.isInteractive}")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        //token을 서버로 전송

    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        //수신한 메시지를 처리
        pm = getSystemService(Context.POWER_SERVICE) as PowerManager

        Log.i("MESSAGE!", "${pm.isInteractive}")
        Log.i("MESSAGE!", "${message.toString()}")

//        val notificationManager = NotificationManagerCompat.from(
//            applicationContext
//        )
//
//        var builder: NotificationCompat.Builder? = null
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
//                val channel = NotificationChannel(
//                    CHANNEL_ID,
//                    CHANNEL_NAME,
//                    NotificationManager.IMPORTANCE_DEFAULT
//                )
//                notificationManager.createNotificationChannel(channel)
//            }
//            builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
//        } else {
//            builder = NotificationCompat.Builder(applicationContext)
//        }
//
//        val title: String = remoteMessage.getNotification().getTitle()
//        val body: String = remoteMessage.getNotification().getBody()
//
//        builder!!.setContentTitle(title)
//            .setContentText(body)
//            .setSmallIcon(R.drawable.ic_launcher_background)
//
//        val notification: Notification = builder!!.build()
//        notificationManager.notify(1, notification)

    }
}

