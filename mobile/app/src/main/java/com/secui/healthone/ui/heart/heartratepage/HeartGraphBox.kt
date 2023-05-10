package com.secui.healthone.ui.heart.heartratepage

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.jaikeerthick.composable_graphs.color.BarGraphColors
import com.jaikeerthick.composable_graphs.composables.BarGraph
import com.jaikeerthick.composable_graphs.composables.LineGraph
import com.jaikeerthick.composable_graphs.data.GraphData
import com.jaikeerthick.composable_graphs.style.BarGraphStyle
import com.jaikeerthick.composable_graphs.style.BarGraphVisibility
import com.secui.healthone.R
import com.secui.healthone.data.heart.HeartRead
import com.secui.healthone.ui.common.AppColors

@Composable
fun HeartGraphBox(
    modifier: Modifier = Modifier,
    heartList: MutableList<HeartRead>
){
    
    Column(modifier = Modifier
        .border(1.dp, AppColors.blue100, RectangleShape)
        .fillMaxWidth()
        .height(196.dp)
    ) {
        val xAxisDataList = mutableListOf<String>();
        val yAxisDataList = mutableListOf<Int>();
        for(h in heartList){
            xAxisDataList.add(h.createTime)
            yAxisDataList.add(h.count)
        }

        LineGraph(
            xAxisData = listOf("Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat").map {
                GraphData.String(it)
            }, // xAxisData : List<GraphData>, and GraphData accepts both Number and String types
            yAxisData = listOf(200, 40, 60, 450, 700, 30, 50),
        )
    }


//    BarGraph(
//        dataList = listOf(20, 30, 10, 60, 35),
//    )


}