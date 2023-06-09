package com.secui.healthone.ui.walking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.secui.healthone.constant.AppColors
import com.secui.healthone.constant.PageRoutes

@Composable
fun AchievementRate(percentage: Float, navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text ="오늘 당신의 걸음 목표 달성률은?",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(48.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        ProgressBar(percentage = percentage)
    }
}

@Composable
fun ProgressBar(percentage: Float) {
    val filledWidth = (percentage * 100).toInt()
    val displayText = "${filledWidth}%"

    Box(
        modifier = Modifier
            .height(24.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(color = AppColors.mono200)
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(fraction = percentage)
                .background(color = AppColors.green600)
        )

        Text(
            text = displayText,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}