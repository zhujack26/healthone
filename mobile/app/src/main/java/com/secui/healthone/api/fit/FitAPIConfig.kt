package com.secui.healthone.api.fit

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType


class FitAPIConfig  {
    companion object {
        // 상수들
        const val GOOGLE_FIT_PERMISSION_REQUEST_CODE = 1;
        const val APP_PERMISSION_REQUEST_CODE = 2;
        const val MY_TAG = "FIT_________"
        const val ERR_TAG = "FIT___ERR____"

        // 함수들
        fun askFitAPIPermission(context: Context, thisActivity:Activity){
            val account:GoogleSignInAccount = getGoogleSignInAccount(context)
            val fitnessOptions = getFitnessOptions()

            if(!GoogleSignIn.hasPermissions(account, fitnessOptions)){ // 구글 핏 API로 부터 권한 설정
                GoogleSignIn.requestPermissions(
                    thisActivity, // your activity
                    GOOGLE_FIT_PERMISSION_REQUEST_CODE, // e.g. 1
                    account,
                    fitnessOptions)

                if(GoogleSignIn.hasPermissions(account, fitnessOptions)){
                    requestAppPermission(context = context, thisActivity=thisActivity)
                }


            }else {
                // Toast.makeText(context, context.getString(R.string.reject_permission), Toast.LENGTH_SHORT).show()
                requestAppPermission(context = context, thisActivity=thisActivity)
            }
        }


        fun requestAppPermission(context: Context, thisActivity: Activity){
            if (ContextCompat.checkSelfPermission(thisActivity, Manifest.permission.ACTIVITY_RECOGNITION) // 걸음 수 권한
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(thisActivity, Manifest.permission.BODY_SENSORS) // 심박수를 위한 권한
                != PackageManager.PERMISSION_GRANTED) {

                // 권한이 없을 경우 사용자에게 권한 요청 모달을 띄우는 코드
                // Permission is not granted
                ActivityCompat.requestPermissions(thisActivity,
                    arrayOf( // 권한 배열들
                        Manifest.permission.ACTIVITY_RECOGNITION,
                        Manifest.permission.BODY_SENSORS
                    ),
                    APP_PERMISSION_REQUEST_CODE
                )


            } else {
                // Toast.makeText(context, "앱 "+context.getString(R.string.reject_permission), Toast.LENGTH_SHORT).show()
            }
        }

        // 구글 Account 가져오기
        fun getGoogleSignInAccount(context:Context):GoogleSignInAccount {
            val fitnessOptions = getFitnessOptions(); // fit API 옵션 가져오기
            return GoogleSignIn.getAccountForExtension(context, fitnessOptions) // GoogleSignInAccount 리턴
        }

        // fitness Option 가져오기
        fun getFitnessOptions(): GoogleSignInOptionsExtension {
            return FitnessOptions.builder() // 구글 fit API fitnessOption 리턴
                // 걸음수 전체 읽고 쓰기 옵션
                .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_WRITE)
                // 걷은 거리
                .addDataType(DataType.TYPE_DISTANCE_DELTA, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.TYPE_DISTANCE_DELTA, FitnessOptions.ACCESS_WRITE)
                // 일정기간 동안의 걸음 수 읽고 쓰기 옵션
                .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_WRITE)
                // 심박수 측정
                .addDataType(DataType.AGGREGATE_HEART_RATE_SUMMARY, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.AGGREGATE_HEART_RATE_SUMMARY, FitnessOptions.ACCESS_WRITE)

                // 영양 정보?
                .addDataType(DataType.AGGREGATE_NUTRITION_SUMMARY, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.AGGREGATE_NUTRITION_SUMMARY, FitnessOptions.ACCESS_WRITE)

                // 수면 정보
                .addDataType(DataType.TYPE_SLEEP_SEGMENT, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.TYPE_SLEEP_SEGMENT, FitnessOptions.ACCESS_WRITE)
                .build()
        }
    }
}

