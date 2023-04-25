package com.secui.healthone.ui.stressindexpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.secui.healthone.R

@Composable
fun RecommandItem(modifier: Modifier=Modifier,

                  ){
    Column(modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight()
        .padding(16.dp)) {


        Image(
            painter = painterResource(id = R.drawable.recommand_sample1),
            contentDescription = StressRecommendBoxText.imgDescString,
            modifier = Modifier
                .width(128.dp)
                .height(128.dp),
            contentScale = ContentScale.Crop,
        )

    }
}