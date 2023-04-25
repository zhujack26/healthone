package com.secui.healthone.ui.stressindexpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.secui.healthone.R

@Composable
fun RecommandItem(
    navController: NavHostController,
    modifier: Modifier=Modifier,
){
    Column(modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight()
        .padding(8.dp)) {

        Image(
            painter = rememberImagePainter("https://picsum.photos/200"),
            contentDescription = StressRecommendBoxText.imgDescString,
            modifier = Modifier
                .width(128.dp)
                .height(128.dp)
                .clickable {

                },
            contentScale = ContentScale.Crop,
        )

    }
}