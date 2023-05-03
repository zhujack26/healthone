package com.secui.healthone.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AlarmSettingPage() {
    val items = listOf(
        "알림 끄기",
        "공지사항",
        "챌린지",
        "식단",
        "걷기",
        "수면관리",
        "건강점수",
        "추천정보"
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items.forEach { item ->
            val isChecked = remember { mutableStateOf(false) }
            AlarmRow(item, isChecked.value) { checked -> isChecked.value = checked }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun AlarmRow(text: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
    }
}