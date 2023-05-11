package com.secui.healthone.ui.overviewpage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.secui.healthone.R
import com.secui.healthone.ui.common.AppColors
import com.secui.healthone.util.BoxTool
import com.secui.healthone.constant.PageRoutes

@Composable
fun UserWalkBox(
    navController: NavHostController,
    walkValue:Int = 0,
    modifier: Modifier = Modifier
){
    val targetWalkNumber = 6000;
    val displayWalkValue:String = BoxTool.getDisplayString(walkValue);

    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .clickable {
                navController.navigate(PageRoutes.Walking.route)
            }
            .background(AppColors.white)
            .padding(16.dp)
    ) {
        Column(modifier= Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) 
        {
            Text(text = "오늘의 걸음 수" ,
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
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
                Text(text = "${displayWalkValue} / ${targetWalkNumber}" ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "조금만 더 힘내면 오늘의 목표를 달성할 수 있어요" ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )


            }
        }
    }
}