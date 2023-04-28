package com.secui.healthone.ui.overviewpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavHostController
import com.secui.healthone.R
import com.secui.healthone.ui.common.AppColors
import com.secui.healthone.util.PageRoutes

@Composable
fun StressIndexBox (
    navController: NavHostController,
    modifier: Modifier = Modifier
){

    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .background(AppColors.white)
            .padding(16.dp).clickable {
                navController.navigate(PageRoutes.StressIndex.route)
            }
    ) {

        Column(modifier= Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                Text(text = "오늘 당신의 스트레스 지수는?", fontSize = 16.sp)
                Image(painter = painterResource(id = R.drawable.ic_info),
                    contentDescription ="스트레스 지수" ,
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
                ) {
                // TODO... local width로 상대적 단위 적용
                Box(modifier = modifier
                    .width(128.dp)
                    .height(16.dp)
                    .background(AppColors.blue300))
                Box(modifier = modifier
                    .width(128.dp)
                    .height(16.dp)
                    .background(AppColors.blue100))
            }

        }

    }
}