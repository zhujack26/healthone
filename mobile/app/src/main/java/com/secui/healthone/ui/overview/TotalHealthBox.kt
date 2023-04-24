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
import com.secui.healthone.R;


@Composable
fun TotalHealthBox(
    modifier: Modifier = Modifier
){
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .background(colorResource(id = R.color.white))
            .padding(16.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            ,verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Row(modifier= Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)) {
                Text(text = "일일 활동", fontSize = 16.sp)
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
                        painter = painterResource(id = R.drawable.ic_circle),
                        contentDescription = "img",
                        modifier = Modifier
                            .width(64.dp)
                            .height(64.dp)
                    )
                    Text(text = "3456", fontSize = 16.sp)
                }

                Column(modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_circle),
                        contentDescription = "img",
                        modifier = Modifier
                            .width(64.dp)
                            .height(64.dp)
                    )
                    Text(text = "3456", fontSize = 16.sp)
                }

                Column(modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_circle),
                        contentDescription = "img",
                        modifier = Modifier
                            .width(64.dp)
                            .height(64.dp)
                    )
                    Text(text = "3456", fontSize = 16.sp)
                }
            }
        }
    }
}