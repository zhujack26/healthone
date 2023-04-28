package com.secui.healthone.ui.heart.heartratepage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.R
import com.secui.healthone.ui.common.AppColors

@Composable
fun HeartBpmGraph(
    modifier: Modifier = Modifier
){
    Column(modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Box(modifier = Modifier
                .width(128.dp)
                .height(18.dp)
                .background(AppColors.blue300)
            )
            Box(modifier = Modifier
                .width(128.dp)
                .height(18.dp)
                .background(AppColors.blue100)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier
            .width(256.dp)
            .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            Text(
                text = HeartRateInfoBoxText.heartRateMin,
                fontSize = 16.sp,
                textAlign = TextAlign.Center);
            Text(
                text = HeartRateInfoBoxText.heartRateMax,
                fontSize = 16.sp,
                textAlign = TextAlign.Center);
        }
    }
}