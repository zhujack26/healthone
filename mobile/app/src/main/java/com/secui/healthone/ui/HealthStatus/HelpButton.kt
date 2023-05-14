package com.secui.healthone.ui.HealthStatus

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.constant.AppColors

@Composable
fun HelpButton(text: String, state:String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(100.dp)
            .border(1.dp, if (isSelected) getColor(state) else AppColors.mono200, RoundedCornerShape(4.dp)), // 선택된 경우와 선택되지 않은 경우의 border 색깔을 다르게 설정합니다.
        colors = ButtonDefaults.outlinedButtonColors(contentColor = if (isSelected) getColor(state) else AppColors.mono200) // 선택된 경우와 선택되지 않은 경우의 텍스트 색깔을 다르게 설정합니다.
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = text, fontSize = 14.sp, style = TextStyle(color = AppColors.mono900)) // 각 Text의 색깔을 설정합니다.
            Text(text = getEmoji(state), fontSize = 28.sp)
            Text(text = state, fontSize = 14.sp, style = TextStyle(color = getColor(state) ))
        }
    }
}

// 상태에 따른 색깔을 반환하는 함수입니다.
fun getColor(state: String) = when(state) {
    "정상" -> AppColors.green200
    "주의" -> AppColors.orange200
    "위험" -> AppColors.red200
    else -> Color.Gray
}

// 상태에 따른 이모지를 반환하는 함수입니다.
fun getEmoji(state: String) = when (state) {
    "정상" -> "😊"
    "주의" -> "😞"
    "위험" -> "😰"
    else -> ""
}



fun onButtonClick(buttonText: String) {
    when (buttonText) {
        "체중" -> {
            // 체중 버튼 기능 구현
        }

        "BMI" -> {
            // BMI 버튼 기능 구현
        }

        "체지방" -> {
            // 체지방 버튼 기능 구현
        }

        "골격근" -> {
            // 골격근 버튼 기능 구현
        }

        "복부비만" -> {
            // 복부비만 버튼 기능 구현
        }

        "혈관" -> {
            // 혈관 버튼 기능 구현
        }

        "혈압" -> {
// 혈압 버튼 기능 구현
        }
        "공복혈당" -> {
// 혈압 버튼 기능 구현
        }
    }
}