package com.secui.healthone.ui.heartratepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.R;

@Composable
fun HeartRateRecordItem(
    modifier: Modifier = Modifier
){
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .background(colorResource(id = R.color.white))
    ) {
        Column(modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 측정 값 + 측정 날짜
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {

                    Text(text = HeartRateRecordItemText.heartBpmValue,
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.mono900)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = HeartRateRecordItemText.heartBpmUnit,
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.mono900)
                    )
                }

                Text(text = HeartRateRecordItemText.heartBpmDate,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.mono900)
                )

            }

            Spacer(modifier = Modifier.height(16.dp));

            // 측정 장비 정보 + 측정 시간
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = HeartRateRecordItemText.heartBpmTime,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.mono900)
                )
                Text(text = HeartRateRecordItemText.wearableDeviceName,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.mono900)
                )

            }

        }

    }

}

class HeartRateRecordItemText {
    companion object {
        const val heartBpmValue = "82"
        const val heartBpmUnit = "bpm"
        const val heartBpmDate = "2023년 4월 10일"
        const val heartBpmTime = "오후 06:10 "
        const val wearableDeviceName = "Galaxy Watch4 Classic"

    }
}