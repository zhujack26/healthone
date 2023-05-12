package com.secui.healthone.ui.walking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.secui.healthone.constant.AppColors

@Composable
fun WalkingGraph(navController: NavController) {
    val stepsData = listOf(1000, 1000, 750, 300, 1200, 800, 100, 0, 900, 400, 600, 0, 1000, 500, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    Column {
        WalkingDetailGraph(stepsData)
    }
}
@Composable
fun WalkingDetailGraph(stepsData: List<Int>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        stepsData.forEachIndexed { index, steps ->
            val barHeight = steps * 0.07f
            Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier.height(100.dp)) {
                Box(
                    modifier = Modifier
                        .height(barHeight.dp)
                        .width(8.dp)
                        .background(color = AppColors.green400)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .width(8.dp)
                        .height(8.dp)
                        .background(color = AppColors.mono400, shape = CircleShape)
                )
            }
        }
    }
}

