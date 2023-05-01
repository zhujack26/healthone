package com.secui.healthone.ui.walking

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.secui.healthone.R

@Composable
fun WalkingType(
    todaySteps: Int,
    highestSteps: Int,
    totalSteps: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        WalkingStatItem(icon = R.drawable.ic_walking, value = todaySteps, label = "오늘 걸음 수")
        WalkingStatItem(icon = R.drawable.ic_walking, value = highestSteps, label = "최고 걸음 수")
        WalkingStatItem(icon = R.drawable.ic_walking, value = totalSteps, label = "총 걸음 수")
    }
}

@Composable
fun WalkingStatItem(
    icon: Int,
    value: Int,
    label: String
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Text(text = value.toString(), textAlign = TextAlign.Center)
        Text(text = label, textAlign = TextAlign.Center)
    }
}