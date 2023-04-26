package com.secui.healthone.ui.heartratepage

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.jaikeerthick.composable_graphs.color.BarGraphColors
import com.jaikeerthick.composable_graphs.composables.BarGraph
import com.jaikeerthick.composable_graphs.style.BarGraphStyle
import com.jaikeerthick.composable_graphs.style.BarGraphVisibility
import com.secui.healthone.R

@Composable
fun HeartGraphBox(
    modifier: Modifier = Modifier
){
    
    Column(modifier = Modifier
        .border(1.dp, colorResource(id = R.color.black), RectangleShape)
        .fillMaxWidth()
        .height(196.dp)
    ) {
        
    }


//    BarGraph(
//        dataList = listOf(20, 30, 10, 60, 35),
//    )


}