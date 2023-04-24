package com.secui.healthone.ui.overview

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
import com.secui.healthone.R


@Composable
fun HealthScoreBox(
    modifier: Modifier= Modifier
){
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .background(colorResource(id = R.color.white))
            .padding(16.dp)
    ) {

        Column(modifier= Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(text = "홍길동님의 건강 신호등",
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight())
            Spacer(modifier = Modifier.height(16.dp))
            Image(painter = 
                painterResource(id = R.drawable.ic_signal), 
                contentDescription = "건강신호등",
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "건강상태 : 양호", fontSize = 16.sp);

        }

    }

}
