package com.secui.healthone.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.secui.healthone.R
import com.secui.healthone.ui.heartmeasurepage.HeartMeasureItem


@Composable
fun HeartMeasurePage(
    navController: NavHostController,
    modifier: Modifier = Modifier
){

    Column(modifier= Modifier
        .fillMaxSize()
        .verticalScroll
            (rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        HeartMeasureItem();
        Spacer(modifier = Modifier.height(32.dp));
        Button( colors = ButtonDefaults
            .outlinedButtonColors(
                backgroundColor =
                colorResource(id = R.color.red100)
            ),
            modifier = Modifier
                .width(196.dp)
                .height(48.dp),
            onClick = {  }) {
            Text(text = "측정 하기",
                fontSize = 20.sp,
                color = colorResource(id = R.color.white)
            )
            
        }
        

    }

}