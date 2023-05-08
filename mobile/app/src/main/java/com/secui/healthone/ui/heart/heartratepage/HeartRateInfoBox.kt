package com.secui.healthone.ui.heart.heartratepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.secui.healthone.R;
import com.secui.healthone.ui.common.AppColors
import com.secui.healthone.util.PageRoutes
import com.secui.healthone.util.PreferenceUtil

@Composable
fun HeartRateInfoBox(
    navController: NavHostController,
    modifier: Modifier = Modifier
){

    val context = LocalContext.current;
    val prefs: PreferenceUtil = PreferenceUtil(context);

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
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            // 날짜
            Text(text = HeartRateInfoBoxText.heartRateDateText,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight());
            Spacer(modifier = Modifier.height(24.dp))
            // bpm text
            Row(modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = prefs.getString("current_heart_bpm", "-"),
                    fontSize = 24.sp) // value
                Spacer(modifier = Modifier.width(8.dp));
                Text(text = HeartRateInfoBoxText.heartRateUnit,
                    fontSize = 20.sp) // unit
            }

            Spacer(modifier = Modifier.height(16.dp))
            // bpm graph
            HeartBpmGraph();
            Spacer(modifier = Modifier.height(32.dp))
            // bpm btn
            Button(colors = ButtonDefaults
                .outlinedButtonColors(
                    backgroundColor =
                    AppColors.red100
                ),
                modifier = Modifier
                    .width(256.dp)
                    .height(48.dp)
                ,
                onClick = {
                    navController.navigate(PageRoutes.HeartMeasure.route)
                }) {
                Text(text = HeartRateInfoBoxText.heartRateBtnText,
                    fontSize = 16.sp,
                    color = AppColors.white
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

class HeartRateInfoBoxText {
    companion object {
        const val heartRateDateText = "2023년 4월 13일"
        const val heartRateValue = "82"
        const val heartRateUnit = "bpm"
        const val heartRateMin = "40"
        const val heartRateMax = "220"
        const val heartRateBtnText = "측정 하기"
    }
}