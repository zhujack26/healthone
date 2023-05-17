package com.secui.healthone.compose.setting

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.secui.healthone.api.downloadData
import com.secui.healthone.constant.AppColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun UserInformDownPage() {
    // Context
    val context = LocalContext.current

    // 암호화된 SharedPreferences 인스턴스
    val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
    val sharedPreferences = EncryptedSharedPreferences.create(
        "secret_shared_prefs",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    // accessToken을 가져오기
    val accessToken = sharedPreferences.getString("access_token", "") ?: ""

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "개인 데이터 다운로드",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = "개인 데이터를 다운로드하려면 아래 버튼을 누르세요. 개인 데이터가 다운로드 됩니다.",
            modifier = Modifier.padding(vertical = 16.dp),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = {
                // "걸음 수 데이터" 다운로드 요청
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        downloadData(context, "https://back.apihealthone.com/check/check-data-download/walk", accessToken)
                    } catch (e: Exception) {
                        Log.e("UserInformDownPage", "걸음 수 데이터 다운로드 중 오류 발생: ${e.message}")
                        e.printStackTrace()
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.green200)
        ) {
            Text(text = "걸음 수 데이터", color = AppColors.white)
        }
        Button(
            onClick = {
                // "수면 데이터" 다운로드 요청
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        downloadData(context, "https://back.apihealthone.com/check/check-data-download/sleep", accessToken)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.green200),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "수면 데이터", color = AppColors.white)
        }
        Button(
            onClick = {
                // "심박수 데이터" 다운로드 요청
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        downloadData(context, "https://back.apihealthone.com/check/check-data-download/heart-rate", accessToken)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.green200),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "심박수 데이터", color = AppColors.white)
        }
        Button(
            onClick = {
                // "회원 건강 데이터" 다운로드 요청
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        downloadData(context, "https://back.apihealthone.com/info/check-data-download/healthinfo", accessToken)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.green200),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "회원 건강 데이터", color = AppColors.white)
        }
        Button(
            onClick = {
                // "회원 건강기록 데이터" 다운로드 요청
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        downloadData(context, "https://back.apihealthone.com/info/check-data-download/sleep", accessToken)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.green200),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "회원 건강기록 데이터", color = AppColors.white)
        }
    }
}