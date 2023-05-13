package com.secui.healthone.ui.heart.heartratepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.R;
import com.secui.healthone.data.heart.HeartRead
import com.secui.healthone.constant.AppColors
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HeartRateRecordItem(
    modifier: Modifier = Modifier,
    heartRate: HeartRead
){
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .background(AppColors.white)
    ) {
        Column(modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                // 날짜
                Text(
                    text = getParsingDateString(heartRate.createTime),
                    fontSize = 14.sp,
                    color = AppColors.mono700
                )

                // 심박수
                Row(modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_heart),
                        contentDescription = "하트",
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.width(16.dp));
                    Row(modifier= Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(text = heartRate.count.toString(), fontSize = 28.sp);
                        Spacer(modifier = Modifier.width((8.dp)));
                        Text(text = "bpm", fontSize = 16.sp);
                    }
                }
            }

        }
    }
}


fun getParsingDateString(inputString:String):String{
    val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val outputFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val date = inputFormatter.parse(inputString)
    return outputFormatter.format(date)
}