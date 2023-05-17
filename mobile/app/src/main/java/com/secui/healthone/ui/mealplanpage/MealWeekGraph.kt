package com.secui.healthone.ui.mealplanpage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaikeerthick.composable_graphs.color.LinearGraphColors
import com.jaikeerthick.composable_graphs.color.PointHighlight
import com.jaikeerthick.composable_graphs.composables.LineGraph
import com.jaikeerthick.composable_graphs.data.GraphData
import com.jaikeerthick.composable_graphs.style.LineGraphStyle
import com.jaikeerthick.composable_graphs.style.LinearGraphVisibility
import com.secui.healthone.constant.AppColors
import com.secui.healthone.repository.CaloriesDataResponse
import java.time.LocalDateTime

@Composable
fun MealWeekGraph(
    modifier: Modifier = Modifier,
    caloriesDataList: List<CaloriesDataResponse>
){
    // LineGraph
    val caloriesGraphStyle = LineGraphStyle(
        visibility = LinearGraphVisibility(
            isHeaderVisible = true,
            isYAxisLabelVisible = false,
            isCrossHairVisible = true
        ),
        colors = LinearGraphColors(
            lineColor = AppColors.green300,
            pointColor = AppColors.green200,
            clickHighlightColor = PointHighlight,
        )
    )

    Column(modifier = Modifier
        .fillMaxWidth()
        .height(196.dp)
    ) {
        if(caloriesDataList.isNotEmpty()){
            val xAxisDataList = mutableListOf<String>();
            val yAxisDataList = mutableListOf<Int>();

            for(c in caloriesDataList.indices){
                val idx = caloriesDataList.size - c -1;
                val LocalDateTime = LocalDateTime.parse(caloriesDataList[idx].createTime);
                val dateString = "${LocalDateTime.monthValue}/${LocalDateTime.dayOfMonth}"
                xAxisDataList.add(dateString)
                val totalCalories = caloriesDataList[idx].sumKcalEaten - caloriesDataList[idx].sumKcalConsume
                yAxisDataList.add(totalCalories);
            }

            LineGraph(
                xAxisData = xAxisDataList.toList().map {
                    GraphData.String(it)
                },
                yAxisData = yAxisDataList.toList(),
                style = caloriesGraphStyle
            )
        }
    }
}
