package com.secui.healthone.ui.overviewpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.R;
import com.secui.healthone.constant.AppColors
import com.secui.healthone.util.BoxTool


@Composable
fun TotalHealthBox(
    stepValue:Int,
    sleepValue:Int,
    calValue:Int,
    modifier: Modifier = Modifier
){

    val stepDisplayValue = BoxTool.getDisplayString(value = stepValue); // 걸음 수
    val sleepDisplayValue = BoxTool.getSleepDisplayString(value = sleepValue); // bpm 값
    val calDisplayValue = BoxTool.getDisplayString(value = calValue); // 칼로리

    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .background(AppColors.white)
            .padding(16.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            ,verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Row(modifier= Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)) {
                Text(
                    text = stringResource(R.string.total_health_box_title),
                    fontSize = 16.sp
                )
            }

            Row(modifier= Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Column(modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier.size(48.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_walking_svg),
                        contentDescription = "Some icon",
                        tint = AppColors.mono700
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stepDisplayValue+" 걸음",
                        fontSize = 16.sp
                    )
                }
                Column(modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_time),
                        contentDescription = "수면 시간",
                        modifier = Modifier
                            .width(48.dp)
                            .height(48.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = sleepDisplayValue,
                        fontSize = 16.sp
                    )
                }

                Column(modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_fire),
                        contentDescription = "img",
                        modifier = Modifier
                            .width(48.dp)
                            .height(48.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = calDisplayValue,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
