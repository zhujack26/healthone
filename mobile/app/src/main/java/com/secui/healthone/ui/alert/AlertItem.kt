package com.secui.healthone.ui.alert

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.secui.healthone.R
import com.secui.healthone.constant.AppColors
import com.secui.healthone.viewmodel.AlertViewModel

@Composable
fun AlertItem(
    navController: NavController,
    alert: AlertViewModel.AlertItemText,
    modifier: Modifier = Modifier
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .topBorder(1.dp, AppColors.mono300)
            .padding(16.dp),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
            Surface(modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
                shape = CircleShape) {
                Image(
                    painter = painterResource(id = R.drawable.recommand_sample1),
                    contentDescription = "이미지",
                    modifier = Modifier
                        .width(64.dp)
                        .height(64.dp),
                    contentScale = ContentScale.Crop,
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .weight(2f)) {

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = alert.alertType, fontSize = 16.sp)
                    Text(text = alert.alertTime, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = alert.alertContent,
                    fontSize = 14.sp,
                    color = AppColors.mono700
                )

            }
    }
}


//  top Border draw function
fun Modifier.topBorder(strokeWidth: Dp, color: Color) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = size.width;
            val height = size.height - strokeWidthPx/2

            drawLine(
                color = color,
                start = Offset(x = width, y = 0f),
                end = Offset(x = 0f , y = 0f),
                strokeWidth = strokeWidthPx
            )
        }
    }
)
