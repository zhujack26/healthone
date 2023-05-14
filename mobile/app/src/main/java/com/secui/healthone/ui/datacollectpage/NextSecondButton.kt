package com.secui.healthone.ui.datacollectpage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.secui.healthone.R
import com.secui.healthone.constant.AppColors
import com.secui.healthone.constant.PageRoutes

@Composable
fun NextSecondButton(navController: NavController) {
    Button(
        onClick = {
            navController.navigate(PageRoutes.Guide.route)
        },
        modifier = Modifier
            .width(280.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(AppColors.mono200),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AppColors.green200
        )
    ) {
        Text(
            text = "다음",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = AppColors.white
        )
    }
}