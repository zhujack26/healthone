package com.secui.healthone.ui.common.stressindexpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.secui.healthone.R
import com.secui.healthone.util.PageRoutes

@Composable
fun StressInfoBox(
    // navController: NavController,
    modifier: Modifier = Modifier
){

    val localWidth = LocalConfiguration.current.screenWidthDp
    val halfWidth = localWidth.dp.minus(64.dp).div(2.dp)// ?

    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .background(colorResource(id = R.color.white))
            .padding(16.dp)
            .clickable { }
    ) {

        Column(modifier= Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
        )
        {
            Text(text = StressInfoBoxText.titleString, fontSize = 16.sp);

            Spacer(modifier = Modifier.height(16.dp));

            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // TODO... local width로 상대적 단위 적용
                Box(modifier = modifier
                    .width(halfWidth.dp) // to calc
                    .height(16.dp)
                    .background(colorResource(id = R.color.blue300)))
                Box(modifier = modifier
                    .width(halfWidth.dp) // to calc
                    .height(16.dp)
                    .background(colorResource(id = R.color.blue100)))
            }

            Spacer(modifier = Modifier.height(16.dp));

            Text(text = StressInfoBoxText.guideString, fontSize = 16.sp);

            Spacer(modifier = Modifier.height(16.dp));

            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = colorResource(id = R.color.white)),
                    modifier = Modifier
                ) {
                    Text(text = StressInfoBoxText.breathBtnString,
                        fontSize = 16.sp,
                        color = colorResource(
                        id = R.color.black
                    ))
                }

            }
        }
    }
}

class StressInfoBoxText {
    companion object {
        const val titleString = "오늘 당신의 스트레스 지수는?"
        const val guideString = "편안한 마음을 위해 깊은 호흡 한번 어떠세요?"
        const val breathBtnString = "호흡 하기"
    }
}