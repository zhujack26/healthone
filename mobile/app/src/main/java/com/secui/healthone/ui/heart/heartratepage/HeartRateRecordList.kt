package com.secui.healthone.ui.heart.heartratepage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.data.heart.HeartRead

@Composable
fun HeartRateRecordList(
    modifier: Modifier = Modifier,
    heartList:MutableList<HeartRead> = mutableListOf()
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
        var idx=  0;
        repeat(heartList.size){
            HeartRateRecordItem(heartRate = heartList.get(idx++));
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

class HeartRateRecordListText {
    companion object {
        const val heartRateRecordListTitle = "측정 기록"
    }
}