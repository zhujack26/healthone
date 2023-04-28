package com.secui.healthone.ui.overviewpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.R;
import com.secui.healthone.ui.common.AppColors


@Composable
fun TotalHealthBox(
    modifier: Modifier = Modifier
){
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
                    text = TotalHealthBoxText.totalHealthBoxTitle,
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
                    Image(
                        painter = painterResource(id = R.drawable.ic_walk),
                        contentDescription = "걸음 수",
                        modifier = Modifier
                            .width(48.dp)
                            .height(48.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = TotalHealthBoxText.totalWalkValue,
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
                        text = TotalHealthBoxText.totalSleepValue,
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
                        text = TotalHealthBoxText.totalCaloriesValue,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

class TotalHealthBoxText {
    companion object {
        const val totalHealthBoxTitle = "일일 활동"
        const val totalWalkValue = "4631 걸음"
        const val totalSleepValue = "6시간 45분"
        const val totalCaloriesValue = "1680 Kcal"

    }
}