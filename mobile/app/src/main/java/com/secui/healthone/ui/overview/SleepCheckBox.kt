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
fun SleepCheckBox(
    modifier: Modifier=Modifier
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        )
        {
            Text(text = "수면은 충분히 취하셨나요?",
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight());
            Spacer(modifier = Modifier.height(32.dp));
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "6시간 45분", fontSize = 20.sp);
                Image(painter = painterResource(
                    id = R.drawable.ic_circle),
                    contentDescription = "수면 아이콘",
                    modifier = Modifier.width(24.dp).height(24.dp)
                )
            }
        }
    }

}
