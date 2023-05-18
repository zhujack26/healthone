package com.secui.healthone.ui.heart.heartratepage

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaikeerthick.composable_graphs.color.Gradient2
import com.jaikeerthick.composable_graphs.color.Gradient3
import com.jaikeerthick.composable_graphs.color.GraphAccent2
import com.jaikeerthick.composable_graphs.color.LinearGraphColors
import com.jaikeerthick.composable_graphs.color.PointHighlight
import com.jaikeerthick.composable_graphs.color.PointHighlight2
import com.jaikeerthick.composable_graphs.composables.LineGraph
import com.jaikeerthick.composable_graphs.data.GraphData
import com.jaikeerthick.composable_graphs.style.LineGraphStyle
import com.jaikeerthick.composable_graphs.style.LinearGraphVisibility
import com.secui.healthone.data.heart.HeartRead
import com.secui.healthone.constant.AppColors
import com.secui.healthone.viewmodel.HeartRateViewModel
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun HeartGraphBox(
    modifier: Modifier = Modifier,
    heartList: MutableList<HeartRead>
){

    if(heartList.size>0){

        // LineGraph
        val heartGraphStyle = LineGraphStyle(
            visibility = LinearGraphVisibility(
                isHeaderVisible = true,
                isYAxisLabelVisible = false,
                isCrossHairVisible = true
            ),
            colors = LinearGraphColors(
                lineColor = AppColors.red300,
                pointColor = AppColors.red200,
                clickHighlightColor = PointHighlight,
            )
        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .height(196.dp)
        ) {

            if(!HeartRateViewModel.heartRateList.value.isEmpty()
                && HeartRateViewModel.heartRateList.value.size > 0){

                val xAxisDataList = mutableListOf<String>();
                val yAxisDataList = mutableListOf<Int>();

                for(h in 0..heartList.size-1){
                    val idx = heartList.size - h -1;
                    val LocalDateTime = LocalDateTime.parse(heartList[idx].createTime);
                    val dateString = "${LocalDateTime.monthValue}/${LocalDateTime.dayOfMonth}"
                    xAxisDataList.add(dateString)
                    yAxisDataList.add(heartList[idx].count);
                }

                LineGraph(
                    xAxisData = xAxisDataList.toList().map {
                        GraphData.String(it)
                    },
                    yAxisData = yAxisDataList.toList(),
                    style = heartGraphStyle
                )
            }
        }

    } else {
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "불러올 데이터가 없습니다. \n 데이터를 기록해보세요!",
                fontSize = 16.sp,
                color = AppColors.mono700
            )
        }
    }
}