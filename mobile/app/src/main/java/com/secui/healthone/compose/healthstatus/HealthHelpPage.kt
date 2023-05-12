package com.secui.healthone.compose.healthstatus

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.secui.healthone.constant.AppColors

@Composable
fun HealthHelpPage(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(AppColors.mono200)
        )

        val scrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            HelpButton(text = "체중", onClick = { onButtonClick("체중") })
            HelpButton(text = "BMI", onClick = { onButtonClick("BMI") })
            HelpButton(text = "체지방", onClick = { onButtonClick("체지방") })
            HelpButton(text = "골격근", onClick = { onButtonClick("골격근") })
            HelpButton(text = "복부비만", onClick = { onButtonClick("복부비만") })
            HelpButton(text = "혈관", onClick = { onButtonClick("혈관") })
            HelpButton(text = "혈압", onClick = { onButtonClick("혈압") })
            HelpButton(text = "공복혈당", onClick = { onButtonClick("공복혈당") })
        }
    }
}

@Composable
fun HelpButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.width(100.dp)
    ) {
        Text(text = text, fontSize = 14.sp)
    }
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