package com.secui.healthone.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.secui.healthone.R
import com.secui.healthone.ui.stress.stressindexpage.StressGraphBox
import com.secui.healthone.ui.stress.stressindexpage.StressInfoBox
import com.secui.healthone.ui.stress.stressindexpage.StressRecommendBox

@Composable
fun StressIndexPage(
    navController:NavHostController,
    modifier: Modifier = Modifier
){
    Column(modifier= Modifier
        .fillMaxSize()
        .verticalScroll
            (rememberScrollState()))
    {

        StressGraphBox();
        StressInfoBox(navController);
        StressRecommendBox(navController);
        Spacer(modifier = Modifier.height(64.dp))
    }
}
