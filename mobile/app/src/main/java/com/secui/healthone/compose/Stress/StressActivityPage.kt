package com.secui.healthone.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.secui.healthone.ui.stress.stressactivitypage.ActivityProgrammeBox
import com.secui.healthone.ui.stress.stressactivitypage.ActivityTipsterBox
import com.secui.healthone.ui.stress.stressactivitypage.StressActivityImage

@Composable
fun StressActivityPage(
    navController: NavHostController,
    modifier: Modifier=Modifier){

    Column(modifier= Modifier
        .fillMaxSize()
        .verticalScroll
            (rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        StressActivityImage();
        ActivityProgrammeBox();
        Spacer(modifier = Modifier.height(32.dp));
        ActivityTipsterBox();

    }


}