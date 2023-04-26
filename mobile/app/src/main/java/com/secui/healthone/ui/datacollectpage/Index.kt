package com.secui.healthone.ui.datacollectpage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.R

@Composable
fun Index(number: Int, filled: Boolean) {
    val backgroundColor = if (filled) colorResource(id = R.color.black) else colorResource(id = R.color.mono200)
    val textColor = if (filled) colorResource(id = R.color.white) else colorResource(id = R.color.black)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(32.dp)
            .background(backgroundColor, CircleShape),
    ) {
        Text(
            text = number.toString(),
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
