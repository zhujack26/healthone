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
import com.secui.healthone.util.PageRoutes


@Composable
fun FoodCalorieBox(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .background(colorResource(id = R.color.white))
            .padding(16.dp)
            .clickable { navController.navigate(PageRoutes.MealPlan.route) }
    ) {

        Column(modifier= Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
        )
        {
            Text(text = "섭취 칼로리", fontSize = 16.sp);
            Spacer(modifier = Modifier.height(16.dp));
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "1024 / 2,150kcal", fontSize = 16.sp)
                Image(painter = painterResource(
                    id = R.drawable.ic_food),
                    contentDescription = "음식 이미지",
                    modifier = Modifier.width(24.dp).height(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp));
            Text(text = "오늘 당신의 섭취한 칼로리를 기록해보세요 ", fontSize = 16.sp);
        }

    }

}

