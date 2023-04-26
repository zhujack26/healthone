package com.secui.healthone.ui.stressbreathpage

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.R

@Composable
fun StressBreathCounter(
    modifier: Modifier = Modifier
){

    // 카운터 계수
    val count: MutableState<Int> = remember {
        mutableStateOf(1)
    }

    // 카운터
    Row(modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.width(8.dp))

        // minus - btn
        Button(
            colors = ButtonDefaults
                .outlinedButtonColors(
                    backgroundColor =
                    colorResource(id = R.color.green200)
                ),
            modifier = Modifier
                .width(48.dp)
                .wrapContentHeight()
                .padding(0.dp)
            ,
            onClick = { if(count.value>1)--count.value else count.value = 1 }) {
            Text(text = "-",
                fontSize = 16.sp,
                color = colorResource(id = R.color.white)
            )
        }

        Spacer(modifier = Modifier.width(32.dp))

        Text(text = count.value.toString(), fontSize = 24.sp);

        Spacer(modifier = Modifier.width(32.dp))

        // plus -btn
        Button(
            colors = ButtonDefaults
                .outlinedButtonColors(
                    backgroundColor =
                    colorResource(id = R.color.green200)
                ),
            modifier = Modifier
                .width(48.dp)
                .wrapContentHeight()
                .padding(0.dp)
            ,
            onClick = { if(count.value<10)count.value++ }) {
            Text(text = "+",
                fontSize = 16.sp,
                color = colorResource(id = R.color.white)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))
    }
    
}