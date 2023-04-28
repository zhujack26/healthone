package com.secui.healthone.ui.stress.stressindexpage

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.jaikeerthick.composable_graphs.composables.BarGraph
import com.jaikeerthick.composable_graphs.data.GraphData
import com.madrapps.plot.line.DataPoint
import com.madrapps.plot.line.LineGraph
import com.madrapps.plot.line.LinePlot
import com.secui.healthone.R
import com.secui.healthone.ui.common.AppColors


@Composable
fun StressGraphBox (
    modifier: Modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight().padding(16.dp)
){

    // 더미데이터
    val dataList = mutableListOf<DataPoint>();
    for(i in 0..20){
        val dataPoint = DataPoint(i.toFloat(), 10*Math.random().toFloat());
        dataList.add(dataPoint);
    }
    dataList.sortedWith(Comparator { t1, t2 -> t1.x.toInt()-t2.x.toInt()  })
    StressIndexLineGraph(listOf(dataList));
}

@Composable
fun StressIndexLineGraph(lines: List<List<DataPoint>>) {
    LineGraph(
        plot = LinePlot(
            listOf(
                LinePlot.Line(
                    lines[0],
                    LinePlot.Connection(color = AppColors.red200),
                    LinePlot.Intersection(color = AppColors.red200),
                    LinePlot.Highlight(color = AppColors.yellow300),
                )
            ),
            grid = LinePlot.Grid(color = AppColors.mono700, steps = 1),
        ),
        modifier = Modifier.fillMaxWidth().height(200.dp),
        onSelection = { xLine, points ->
            // Do whatever you want here
        }
    )
}