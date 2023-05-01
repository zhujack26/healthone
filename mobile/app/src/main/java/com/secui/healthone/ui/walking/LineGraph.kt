package com.secui.healthone.ui.walking

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.madrapps.plot.line.DataPoint
import com.madrapps.plot.line.LineGraph
import com.madrapps.plot.line.LinePlot
import com.secui.healthone.ui.common.AppColors

@Composable
fun LineGraph (
    modifier: Modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight().padding(16.dp)
){

    // 더미데이터
    val dataList = mutableListOf<DataPoint>();
    for(i in 0..10){
        val dataPoint = DataPoint(i.toFloat(), 10*Math.random().toFloat());
        dataList.add(dataPoint);
    }
    dataList.sortedWith(Comparator { t1, t2 -> t1.x.toInt()-t2.x.toInt()  })
    WalkingLineGraph(listOf(dataList));
}

@Composable
fun WalkingLineGraph(lines: List<List<DataPoint>>) {
    LineGraph(
        plot = LinePlot(
            listOf(
                LinePlot.Line(
                    lines[0],
                    LinePlot.Connection(color = AppColors.green500),
                    LinePlot.Intersection(color = AppColors.green300, radius = 2.dp),
                    LinePlot.Highlight(color = AppColors.green700),
                )
            ),
            grid = LinePlot.Grid(color = AppColors.green700, steps = 1),
        ),
        modifier = Modifier.fillMaxWidth().height(200.dp),
        onSelection = { xLine, points ->
        }
    )
}