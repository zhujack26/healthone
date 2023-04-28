package com.secui.healthone.ui.heart.heartmeasurepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.R;

@Composable
fun HeartMeasureItem(
    modifier: Modifier = Modifier
){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = HeartMeasureItemText.HeartMeasureItemTitle,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(painter =
        painterResource(id = R.drawable.ic_heart),
            contentDescription = "하트 아이콘",
            modifier = Modifier
                .width(256.dp)
                .wrapContentHeight(),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(text = HeartMeasureItemText.HeartMeasureValue,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = HeartMeasureItemText.HeartMeasureUnit,
                fontSize = 16.sp
            )

        }

    }
}

class HeartMeasureItemText {
    companion object {
        const val HeartMeasureItemTitle = "심박수 측정하기"
        const val HeartMeasureValue = "-"
        const val HeartMeasureUnit = "bpm"
    }
}