package com.secui.healthone.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.secui.healthone.R
import com.secui.healthone.ui.stressindexpage.StressGraphBox
import com.secui.healthone.ui.stressindexpage.StressInfoBox
import com.secui.healthone.ui.stressindexpage.StressRecommendBox

@Composable
fun StressIndexPage(
    modifier: Modifier =
        Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.black))
){
    Column(modifier= Modifier
        .fillMaxSize()
        .verticalScroll
            (rememberScrollState()))
    {

        StressGraphBox();
        StressInfoBox();
        StressRecommendBox();

    }
}
