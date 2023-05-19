package com.secui.healthone.compose.challenge

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.secui.healthone.ui.challenge.populardetail.PopularDetailItem

@Composable
fun PopularDetailPage(
    navController: NavHostController,
    modifier: Modifier=Modifier)
{
    Column(modifier= Modifier
        .fillMaxSize()
        .verticalScroll
            (rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        PopularDetailItem();

    }
}
