package com.secui.healthone.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.secui.healthone.ui.heart.heartratepage.HeartGraphBox
import com.secui.healthone.ui.heart.heartratepage.HeartRateInfoBox
import com.secui.healthone.ui.heart.heartratepage.HeartRateRecordList

@Composable
fun HeartRatePage(
    navController: NavHostController,
    modifier: Modifier=Modifier){

    Column(modifier= Modifier
        .fillMaxSize()
        .verticalScroll
            (rememberScrollState()))
    {

        HeartGraphBox();
        Spacer(modifier = Modifier.height(16.dp))
        HeartRateInfoBox(navController);
        Spacer(modifier = Modifier.height(16.dp))
        HeartRateRecordList();
        Spacer(modifier = Modifier.height(32.dp))

    }

}