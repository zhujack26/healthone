package com.secui.healthone.ui.heart.heartratepage

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.R;

@Composable
fun HeartRateRecordList(
    modifier: Modifier = Modifier
) {
    Text(text = HeartRateRecordListText.heartRateRecordListTitle,
        fontSize = 20.sp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp, 12.dp)
    )

    Spacer(modifier = Modifier.height(8.dp))

    Column(modifier = Modifier
        .fillMaxWidth()
        .height(512.dp)
        .padding(16.dp)
        .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        repeat(16){
            HeartRateRecordItem()
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

class HeartRateRecordListText {
    companion object {
        const val heartRateRecordListTitle = "측정 기록"
    }
}