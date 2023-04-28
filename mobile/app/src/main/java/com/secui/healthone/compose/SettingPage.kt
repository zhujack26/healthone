package com.secui.healthone.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SettingPage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        val arrow = ">"
        val settingsItems = listOf(
            "기본설정 및 목표 변경하기",
            "이용약관",
            "개인정보 보호방침",
            "개인 데이터 다운로드",
            "개인 데이터 삭제",
            "로그아웃",
            "회원탈퇴"
        )

        settingsItems.forEach { item ->
            SettingsRow(item = item, textStyle = textStyle, arrow = arrow)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun SettingsRow(item: String, textStyle: TextStyle, arrow: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(textStyle.toSpanStyle()) {
                    append(item)
                }
                withStyle(SpanStyle(textStyle.color)) {
                    append(arrow)
                }
            }
        )
    }
}