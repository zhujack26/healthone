package com.secui.healthone.ui.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.secui.healthone.R

@Composable
fun Records(
    totalSteps: String = "69568",
    totalTime: String = "1546분",
    totalCalories: String = "6956kcal",
    bestSteps: String = "11322",
    bestTime: String = "156분",
    bestCalories: String = "695kcal"
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("총 기록 (시작 2023년 1월 1일)")

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RecordItem(
                icon = painterResource(R.drawable.ic_walking),
                value = totalSteps,
                label = "걸음수"
            )
            RecordItem(
                icon = painterResource(R.drawable.ic_time),
                value = totalTime,
                label = "운동 시간"
            )
            RecordItem(
                icon = painterResource(R.drawable.ic_fire),
                value = totalCalories,
                label = "칼로리 소모량"
            )
        }

        Spacer(Modifier.height(16.dp))

        Text("최고 기록")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RecordItem(
                icon = painterResource(R.drawable.ic_walking),
                value = bestSteps,
                label = "걸음수"
            )
            RecordItem(
                icon = painterResource(R.drawable.ic_time),
                value = bestTime,
                label = "운동 시간"
            )
            RecordItem(
                icon = painterResource(R.drawable.ic_fire),
                value = bestCalories,
                label = "칼로리 소모량"
            )
        }
    }
}

@Composable
fun RecordItem(icon: Painter, value: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = icon,
            contentDescription = label,
            modifier = Modifier.size(24.dp)
        )
        Text(value)
        Text(label)
    }
}