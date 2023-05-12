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
    modifier: Modifier = Modifier
){

    val widthList = calcRatioHeart(LocalContext.current);

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
                .width(widthList[0].dp)
                .height(18.dp)
                .background(AppColors.red300)
            )
            Box(modifier = Modifier
                .width(widthList[1].dp)
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

fun calcRatioHeart(context:Context):List<Int>{
    val prefs = PreferenceUtil(context);
    val recordBpm = prefs.getString("current_heart_bpm", "0").toInt();

    Log.d("BPM", "$recordBpm")

    val basicWidth = 256;
    val divValue = 180;
    val standValue = recordBpm-40;
    val heartRatio:Float = (divValue/standValue).toFloat();
    Log.d("RATIO", "${heartRatio}")
    val heartGageWidth = heartRatio*basicWidth.toInt();

    val widthList = listOf<Int>(heartGageWidth.toInt(), (basicWidth-heartGageWidth).toInt())
    Log.d("VALUES:::", "${widthList.toString()}")
    return widthList;
}