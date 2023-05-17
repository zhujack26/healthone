package com.secui.healthone.compose.setting

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.constant.AppColors

@Composable
fun UserInformDownPage() {
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
            onClick = { /* do nothing */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.green200)
        ) {
            Text(text = "걸음 수 데이터", color = AppColors.white)
        }
        Button(
            onClick = { /* do nothing */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.green200),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "수면 데이터", color = AppColors.white)
        }
        Button(
            onClick = { /* do nothing */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.green200),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "심박수 데이터", color = AppColors.white)
        }
        Button(
            onClick = { /* do nothing */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.green200),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "회원 건강 데이터", color = AppColors.white)
        }
        Button(
            onClick = { /* do nothing */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.green200),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "회원 건강기록 데이터", color = AppColors.white)
        }
    }
}