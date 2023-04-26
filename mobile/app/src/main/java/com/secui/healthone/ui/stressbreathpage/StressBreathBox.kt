package com.secui.healthone.ui.stressbreathpage

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.compose.StressBreathPageText

@Composable
fun StressBreathBox(
    modifier: Modifier = Modifier
){
    Row(modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Column(modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()) {

            Text(text = StressBreathPageText.inhaleText, fontSize = 16.sp);
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = StressBreathPageText.inhalePeriod, fontSize = 16.sp);

        }

        Spacer(modifier = Modifier.width(72.dp))

        Column(modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()) {

            Text(text = StressBreathPageText.exhalationText, fontSize = 16.sp);
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = StressBreathPageText.exhalationPeriod, fontSize = 16.sp);
        }
    }
}

