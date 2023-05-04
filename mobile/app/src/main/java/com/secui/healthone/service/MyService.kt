package com.secui.healthone.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.util.Log
import java.util.Timer
import java.util.TimerTask

class MyService : Service() {
    //private lateinit var timer: Timer
    private val interval = 1000L // 10 seconds
    private var count = 0
    private var timer: Timer? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("MyService", "onStartCommand....")
//        timer = Timer()
//        timer.scheduleAtFixedRate(object : TimerTask() {
//            override fun run() {
//                // 주기적으로 실행할 함수를 호출
//                doSomething()
//            }
//        }, 0, interval)

        startTimer()

//        return super.onStartCommand(intent, flags, startId)
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
//        timer = Timer()
//        timer.scheduleAtFixedRate(object : TimerTask() {
//            override fun run() {
//                // 주기적으로 실행할 함수를 호출
//                doSomething()
//            }
//        }, 0, interval)
    }

    override fun onBind(intent: Intent?): IBinder? {
        // onBind 메서드는 서비스가 연결된 경우 호출됩니다.
        // 이 예제에서는 사용하지 않으므로 null 반환
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        // 서비스가 종료될 때 타이머도 함께 종료
        //
        // stopTimer()
        Log.i(MY_TAG, "onDestroy.....")
    }

    private fun doSomething(cnt:Int) {
        // 주기적으로 실행될 함수
        Log.i(MY_TAG, "new Logging.... $cnt")
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        startTimer()
        super.onTaskRemoved(rootIntent)
    }

//    private fun startTimer(){
//        timer?.cancel()
//        // 1초마다 실행되는 TimerTask 생성
//        val task = object : TimerTask() {
//            override fun run() {
//                // count 증가
//                count++
//                // Log.d(TAG, "count: $count")
//                doSomething(count);
//
//                // count가 300을 넘으면 타이머 종료
//                if (count >= 300) {
//                    stopTimer()
//                }
//            }
//        }
//
//        // Timer 객체 생성 및 실행
//        timer = Timer()
//        timer?.schedule(task, 0, 1000)
//
//    }

//    private fun stopTimer() {
//        // Timer 객체 중지
//        timer?.cancel()
//        timer = null
//
//        // 카운트 초기화
//        count = 0
//
//        Log.d(MY_TAG, "timer stopped")
//    }


    private fun startTimer() {
        timer?.cancel()

        // TimerTask를 별도의 스레드에서 실행
        val handlerThread = HandlerThread("MyTimer")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)

        // 1초마다 실행되는 TimerTask 생성
        val task = object : TimerTask() {
            override fun run() {
                handler.post {
                    // count 증가
                    count++
                    doSomething(count)

                    // count가 300을 넘으면 타이머 종료
                    if (count >= 300) {
                        stopTimer()
                    }
                }
            }
        }

        // Timer 객체 생성 및 실행
        timer = Timer()
        timer?.schedule(task, 0, 1000)
    }

    private fun stopTimer() {
        timer?.cancel()
        timer = null
        count = 0

        // Service 종료
        stopSelf()
    }

    companion object {
        const val MY_TAG = "MyService"
    }
}