package com.secui.healthone.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
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
import com.secui.healthone.constant.AppColors

@Composable
fun AlarmSettingPage() {
    val items = listOf(
        "공지사항",
        "챌린지",
        "식단",
        "걷기",
        "수면관리",
        "건강점수",
        "추천정보"
    )
    val allCheckedState = remember { mutableStateOf(false) }
    val individualCheckedStateList = remember { items.map { mutableStateOf(false) } }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        AlarmRow("알림 끄기", allCheckedState.value) { checked ->
            allCheckedState.value = checked
            individualCheckedStateList.forEach { it.value = checked }
        }
        Divider(color = AppColors.mono200, thickness = 2.dp) // "알림끄기" 밑에 크기 2의 줄
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "세부 알림 설정",
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier.padding(start = 32.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        items.forEachIndexed { index, item ->
            AlarmRow(item, individualCheckedStateList[index].value
            ) {
                    checked -> individualCheckedStateList[index].value = checked }
            if (index != items.lastIndex) {
                Divider(color = AppColors.mono200, thickness = 1.dp)
            }
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
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = 32.dp)
        )
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.padding(start = 32.dp)
        )
    }
}