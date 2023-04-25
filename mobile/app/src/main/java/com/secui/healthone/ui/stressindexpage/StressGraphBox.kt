package com.secui.healthone.ui.stressindexpage

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
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


@Composable
fun StressGraphBox (
    modifier: Modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight()
){

    val dataList = mutableListOf<DataPoint>();

    for(i in 0..30){
        val dataPoint = DataPoint(i.toFloat(), 10*Math.random().toFloat());
        dataList.add(dataPoint);
    }

    dataList.sortedWith(Comparator { t1, t2 -> t1.x.toInt()-t2.x.toInt()  })

    SampleLineGraph(listOf(dataList));
    bargraph();
}

@Composable
fun bargraph(modifier: Modifier=Modifier){
    BarGraph(
        dataList = listOf(20, 30, 10, 60, 35), //  dataList : List<Number>
    )
}


@Composable
fun SampleLineGraph(lines: List<List<DataPoint>>) {
    LineGraph(
        plot = LinePlot(
            listOf(
                LinePlot.Line(
                    lines[0],
                    LinePlot.Connection(color = colorResource(id = R.color.red300)),
                    LinePlot.Intersection(color = colorResource(id = R.color.red500)),
                    LinePlot.Highlight(color = colorResource(id = R.color.yellow700)),
                )
            ),
            grid = LinePlot.Grid(color = colorResource(id = R.color.red300), steps = 4),
        ),
        modifier = Modifier.fillMaxWidth().height(200.dp),
        onSelection = { xLine, points ->
            // Do whatever you want here
        }
    )
}