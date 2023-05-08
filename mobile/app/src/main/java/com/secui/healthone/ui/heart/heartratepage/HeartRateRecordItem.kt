package com.secui.healthone.ui.heart.heartratepage

<<<<<<< HEAD
import androidx.compose.foundation.Image
=======
>>>>>>> 48ed6455598a786f90b976d86faa3b9addc1deef
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
<<<<<<< HEAD
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.R;
import com.secui.healthone.data.heart.HeartRead
import com.secui.healthone.ui.common.AppColors
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HeartRateRecordItem(
    modifier: Modifier = Modifier,
    heartRate: HeartRead
=======
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.R;
import com.secui.healthone.ui.common.AppColors

@Composable
fun HeartRateRecordItem(
    modifier: Modifier = Modifier
>>>>>>> 48ed6455598a786f90b976d86faa3b9addc1deef
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
<<<<<<< HEAD
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
=======
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

>>>>>>> 48ed6455598a786f90b976d86faa3b9addc1deef
                Row(modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
<<<<<<< HEAD
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
=======
                    horizontalArrangement = Arrangement.End
                ) {

                    Text(text = HeartRateRecordItemText.heartBpmValue,
                        fontSize = 16.sp,
                        color = AppColors.mono900
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = HeartRateRecordItemText.heartBpmUnit,
                        fontSize = 16.sp,
                        color = AppColors.mono900
                    )
                }

                Text(text = HeartRateRecordItemText.heartBpmDate,
                    fontSize = 16.sp,
                    color = AppColors.mono900
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
                    color = AppColors.mono900
                )
                Text(text = HeartRateRecordItemText.wearableDeviceName,
                    fontSize = 16.sp,
                    color = AppColors.mono900
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
>>>>>>> 48ed6455598a786f90b976d86faa3b9addc1deef
}