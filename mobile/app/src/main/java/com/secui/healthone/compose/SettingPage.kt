package com.secui.healthone.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.clickable


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
            "기본설정 및 목표 변경하기" to "basic_settings",
            "이용약관" to "terms",
            "개인정보 보호방침" to "privacy",
            "개인 데이터 다운로드" to "data_download",
            "개인 데이터 삭제" to "data_deletion",
            "로그아웃" to "logout",
            "회원탈퇴" to "withdraw"
        )

        settingsItems.forEach { (item, route) ->
            SettingsRow(item = item, textStyle = textStyle, arrow = arrow, onClick = { navController.navigate(route) })
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun SettingsRow(item: String, textStyle: TextStyle, arrow: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item,
            style = textStyle
        )
        Text(
            text = arrow,
            style = textStyle
        )
    }
}