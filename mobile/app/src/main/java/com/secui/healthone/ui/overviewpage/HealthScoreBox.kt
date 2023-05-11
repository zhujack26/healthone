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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.secui.healthone.R
import com.secui.healthone.ui.common.AppColors
import com.secui.healthone.constant.PageRoutes


@Composable
fun HealthScoreBox(
    navController: NavHostController,
    modifier: Modifier= Modifier
){
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .background(AppColors.white)
            .padding(16.dp)
            .clickable { navController.navigate(PageRoutes.HealthStatus.route) }
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
            Spacer(modifier = Modifier.height(32.dp))
            Image(painter = 
                painterResource(id = R.drawable.ic_signal), 
                contentDescription = "건강신호등",
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "건강상태 : 양호", fontSize = 16.sp);

        }

    }

}
