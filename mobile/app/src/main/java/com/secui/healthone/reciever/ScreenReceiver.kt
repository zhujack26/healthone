package com.secui.healthone.reciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.secui.healthone.util.DBHelper
import com.secui.healthone.util.PreferenceUtil
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId

class ScreenReceiver : BroadcastReceiver() {

    lateinit var prefs:PreferenceUtil

    override fun onReceive(context: Context?, intent: Intent?) {

        prefs = PreferenceUtil(context!!); // context가 null이 오진 않는다.
        val nowTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

        if (intent?.action == Intent.ACTION_SCREEN_ON) {
            // 스크린 ON
            Log.i("SCREEN", "스크린 켜짐 (ON) >> TIME : ${nowTime.toString()}")
            prefs.setString("wake_time", "${nowTime.toString()}")
            val result = saveUserSleepTime(PreferenceUtil(context));
            Log.i(LOG, "취침 시간 : $result");

            val dbHelper = DBHelper(context);
            if(result > 0){
                // selectAll
                dbHelper.saveScore(context=context, recordSleepTime = result);
            }
            val list = dbHelper.selectAll(context=context);
            Log.i(LOG, "${list.toString()}")

        } else if (intent?.action == Intent.ACTION_SCREEN_OFF) {
            // 스크린 OFF
            Log.i("SCREEN", "스크린 꺼짐 (OFF) >> TIME : ${nowTime.toString()}")
            prefs.setString("sleep_time", "${nowTime.toString()}")
        }
    }

    companion object {
        const val ERR = "SLEEP_ERR!!!!"
        const val LOG = "SLEEP_LOG::::"
    }

    fun saveUserSleepTime(prefs:PreferenceUtil):Long{
        /* 변수 설명
            userWakeTime : 사용자의 기상시간 → 화면이 켜진 시간
            userSleepTime : 사용자의 취침시간 → 화면이 꺼진 시간
            setWakeTime : 사용자가 설정한 기상 시간
            setSleepTime : 사용자가 설정한 취침 시간
         */
        // step1. 기록된 시간의 유효성 체크
        // 기록된 시간1이 유효하지 않으면 시간 판정이 불가하므로 0을 리턴하고 함수 종료
        val userWakeTime = prefs.getString("wake_time", "X")
        val userSleepTime = prefs.getString("sleep_time", "X")

        if(userSleepTime.equals("X") || userWakeTime.equals("X")) {
            Log.e(ERR, "수면 측정 값에 문제 발생 : The sleep measurement value is not perfect.")
            return 0;
        };

        // 측정된 시간 기록을 세팅한다.
        // LDT : LocalDateTime 변수용 접미사
        val userSleepLDT = LocalDateTime.parse(userSleepTime);
        val userWakeLDT = LocalDateTime.parse(userWakeTime);

        // 사용자가 설정한 값이 없을 경우에는 오후 22시 ~ 오전 7시를 기본 값으로 잡는다.
        // 이를 위해 시간처리를 위한 현재시각 기준 localDateTime 객체 생성
        val nowDate = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

        // 세팅 값이 없을 경우에는 아래 선언된 값을 기준으로 시간 계산을 하게 된다.
        var targetWakeLDT =
            LocalDateTime.of(
                userWakeLDT.year,
                userWakeLDT.month,
                userWakeLDT.dayOfMonth,
                7, 0, 0)

        var targetSleepLDT =
            LocalDateTime.of(
                userWakeLDT.year,
                userWakeLDT.month,
                userWakeLDT.dayOfMonth,
                22, 0,0);


        // 사용자가 설정한 취침 시간 객체를 불러온다.
        val setSleepTime = prefs.getString("SLEEP_TIME", "X")
        val setWakeTime = prefs.getString("WAKE_TIME", "X")

        // 값 자체가 유효한지 부터 판단한다.
        // 세팅 값이 없다면, 기본 값을 사용해야 한다.
        val isSetSleep = setSleepTime.equals("X")
        val isSetWake = setWakeTime.equals("X");



        // 수면 값을 설정했다면 세팅 값을 바꿔준다.
        if(!isSetSleep){
            Log.i(LOG, "$setSleepTime")
            val hour = setSleepTime.substring(0, 2);
            val min = setSleepTime.substring(3);

            targetSleepLDT = LocalDateTime.of(
                nowDate.year,
                nowDate.month,
                nowDate.dayOfMonth,
                hour.toInt(), min.toInt(),0);
        }

        // 기상 값을 설정했다면 세팅 값을 바꿔준다.
        if(!isSetWake){
            Log.i(LOG, "$setWakeTime")
            val hour = setWakeTime.substring(0, 2);
            val min = setWakeTime.substring(3);

            targetWakeLDT = LocalDateTime.of(
                nowDate.year,
                nowDate.month,
                nowDate.dayOfMonth,
                hour.toInt(), min.toInt(), 0)
        }


        // condition
        // 취침 시간이 사용자가 설정한 취침시간에 속하거나
        // 기상 시간이 사용자가 설정한 취침시간에 속해야 한다.

        // condition 1-1 취침 시간이 사용자 설정 취침시간 이내인가?
        // compareTo 메서드 사용!
        // 이전이면 음수, 같으면 0, 이후면 양수를 반환
        val sleepSleepGap = userSleepLDT.compareTo(targetSleepLDT); // 수면시간, 설정 수면시간 사이의 차이!
        val sleepWakeGap = userSleepLDT.compareTo(targetWakeLDT); // 수면시간과, 기상시간 사이의 차이! 밤샜는가, 여부!

        // 수면시간 사이에만 비교한다
        val isSleepIn =  if (sleepSleepGap < 0) false // 취침 시간이 이르니?
        else if(sleepSleepGap==0) true // 취침시간 딱맞니?
        else if(sleepSleepGap > 0 && sleepWakeGap <= 0) true // 취침 시간 ~ 기상시간 사이니?
        else false // 넌 뭐니?

//        val wakeWakeGap = userWakeLDT.compareTo(targetWakeLDT); // 기상시간, 설정 기상시간 사이의 차이!
//        val wakeSleepGap = userWakeLDT.compareTo(targetSleepLDT); // 기상시간과 수면 시간의 차이, 목표보다 일찍 잤는지 판단!
//
//        val isWakeIn = if(wakeWakeGap > 0) true // 늦잠이긴 해도~ 일어났니?
//        else if(wakeWakeGap==0) true // 딱 맞게 일어났나?
//        else if(wakeWakeGap < 0 && wakeSleepGap >= 0) true // 일찍 일어나긴 했는데... 그래도 취침시간 내에 일어난거니? >> 유효한 잠으로 판
//        else false // 넌 뭐니?

        if(isSleepIn){// || isWakeIn
            val duration = Duration.between(userSleepLDT, userWakeLDT)
            return duration.seconds;
        }else {
            Log.i(LOG, "유효한 취침 시간이 아니네요.. 기록을 하지 않습니다...")
            return 0
        };
    }
}

