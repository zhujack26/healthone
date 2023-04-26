package com.secui.healthone.ui.stressactivitypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.secui.healthone.R

@Composable
fun StressActivityImage(
    modifier: Modifier = Modifier
){
    val localWidth = LocalConfiguration.current.screenWidthDp;

    Image(
        painter =
        painterResource(id = R.drawable.recommand_sample1),
        contentDescription = "이미지",
        modifier = Modifier
            .width(localWidth.dp)
            .height(224.dp),
        contentScale = ContentScale.Crop
    )

}