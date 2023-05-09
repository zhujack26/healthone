package com.secui.healthone.compose

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController
import com.secui.healthone.R
import com.secui.healthone.data.heart.HeartRead
import com.secui.healthone.data.heart.HeartWrite
import com.secui.healthone.repository.HeartRateRepository
import com.secui.healthone.ui.common.AppColors
import com.secui.healthone.ui.heart.heartmeasurepage.HeartMeasureItem
import com.secui.healthone.util.PreferenceUtil
import com.secui.healthone.viewmodel.HeartRateViewModel


@Composable
fun HeartMeasurePage(
    navController: NavHostController,
    modifier: Modifier = Modifier
){

    Column(modifier= Modifier
        .fillMaxSize()
        .verticalScroll
            (rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        HeartMeasureItem(navController);
    }
}