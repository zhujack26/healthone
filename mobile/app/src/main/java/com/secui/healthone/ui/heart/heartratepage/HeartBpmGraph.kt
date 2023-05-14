package com.secui.healthone.ui.heart.heartratepage

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.constant.AppColors
import com.secui.healthone.util.PreferenceUtil

@Composable
fun HeartBpmGraph(
    modifier: Modifier = Modifier,
    bpm:Int
){

    val bpmWidths:List<Int> = getGraphWidths(bpm);

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
                .width(bpmWidths[0].dp)
                .height(18.dp)
                .background(AppColors.red300)
            )
            Box(modifier = Modifier
                .width(bpmWidths[1].dp)
                .height(18.dp)
                .background(AppColors.red100)
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
fun getGraphWidths(bpm:Int):List<Int>{
    val total = 256;
    val base = (220-40).toFloat();
    val width1:Float = ((bpm-40)/base)*total;
    val width2 = total-width1.toInt();
    return listOf<Int>(width1.toInt(), width2);
}