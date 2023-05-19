package com.secui.healthone.ui.sleep

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.secui.healthone.data.Sleep.SleepRecord
import com.secui.healthone.viewmodel.SleepViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun SleepRecordCard(sleepRecord: SleepRecord, index: Int, viewModel: SleepViewModel) {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

    val startSleepTime = sleepRecord.startSleepTime?.let {
        val parsedStartSleepTime = inputFormat.parse(it)
        outputFormat.format(parsedStartSleepTime)
    }
    val endSleepTime = sleepRecord.endSleepTime?.let {
        val parsedEndSleepTime = inputFormat.parse(it)
        outputFormat.format(parsedEndSleepTime)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 8.dp,
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                startSleepTime?.let {
                    Text(text = "취침 시간: $it")
                }
                Spacer(modifier = Modifier.height(8.dp))
                endSleepTime?.let {
                    Text(text = "기상 시간: $it")
                }
            }

            Box(
                modifier = Modifier
                    .size(40.dp, 20.dp)
                    .background(Color.White, shape = RoundedCornerShape(percent = 50))
                    .border(
                        BorderStroke(1.dp, Color.Gray),
                        shape = RoundedCornerShape(percent = 50)
                    )
                    .clickable { viewModel.deleteSleepRecord(index) }
            ) {
                Text("삭제", color = Color.Black, modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}
