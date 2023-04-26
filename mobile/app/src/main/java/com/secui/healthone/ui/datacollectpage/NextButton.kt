package com.secui.healthone.ui.datacollectpage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.secui.healthone.R

@Composable
fun NextButton(navController: NavController) {
    Box(
        modifier = Modifier
            .width(280.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(colorResource(id = R.color.mono200))
            .clickable { navController.navigate("datacollect2") },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "다음",
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.mono300)
        )
    }
}