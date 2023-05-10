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
import com.secui.healthone.util.BoxTool
import com.secui.healthone.constant.PageRoutes


@Composable
fun SleepCheckBox(
    navController: NavHostController,
    sleepValue:Int = 0,
    modifier: Modifier=Modifier
){

    val sleepDisplayValue = BoxTool.getSleepDisplayString(sleepValue);

    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .background(AppColors.white)
            .padding(16.dp)
            .clickable { navController.navigate(PageRoutes.Sleep.route) }

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
                Text(text = "${sleepDisplayValue}", fontSize = 20.sp);
                Image(painter = painterResource(
                    id = R.drawable.ic_sleep),
                    contentDescription = "수면 아이콘",
                    modifier = Modifier.width(24.dp).height(24.dp)
                )
            }
        }
    }
}
