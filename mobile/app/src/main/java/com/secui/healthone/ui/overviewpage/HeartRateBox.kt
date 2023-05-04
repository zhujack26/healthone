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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.secui.healthone.R
import com.secui.healthone.ui.common.AppColors
import com.secui.healthone.util.BoxTool
import com.secui.healthone.util.PageRoutes
import com.secui.healthone.util.PreferenceUtil

@Composable
fun HeartRateBox(
    navController:NavHostController,
    bpmValue:Int = 0,
    modifier: Modifier = Modifier
){

    val context = LocalContext.current;
    val prefs: PreferenceUtil = PreferenceUtil(context);
    val displayBpmValue:String = BoxTool.getBpmDisplayString(bpmValue);


    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .background(AppColors.white)
            .padding(16.dp).clickable {
              navController.navigate(PageRoutes.HeartRate.route);
            },
    ) {

        Column(modifier= Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "심박 수",
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center
            );
            Spacer(modifier = Modifier.height(16.dp));
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(painter = painterResource(
                    id = R.drawable.ic_heart),
                    contentDescription = "하트 아이콘",
                    modifier= Modifier
                        .width(36.dp)
                        .height(36.dp)
                )
                Spacer(modifier = Modifier.width(16.dp));
                Text(text = "${prefs.getString("current_heart_bpm", "-")} bpm",
                    fontSize = 20.sp);
            }
            Spacer(modifier = Modifier.height(16.dp))

        }
    }

}