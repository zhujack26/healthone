package com.secui.healthone.compose

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController
import com.secui.healthone.data.heart.HeartRead
import com.secui.healthone.repository.HeartRateRepository
import com.secui.healthone.ui.heart.heartratepage.HeartGraphBox
import com.secui.healthone.ui.heart.heartratepage.HeartRateInfoBox
import com.secui.healthone.ui.heart.heartratepage.HeartRateRecordList
import com.secui.healthone.viewmodel.HeartRateViewModel

@Composable
fun HeartRatePage(
    navController: NavHostController,
    modifier: Modifier=Modifier){

    val mOwner = LocalLifecycleOwner.current
    val repository = HeartRateRepository()
    val viewModel = HeartRateViewModel(repository)
    val heartList:MutableState<MutableList<HeartRead>> = remember { mutableStateOf(mutableListOf()) }

    viewModel.getHeartRateList();
    viewModel.heartListResponse.observe(mOwner, Observer{
        //Log.d("HEART_PAGE:::", "${it.toString()}")
        heartList.value = it;
    })

    Column(modifier= Modifier
        .fillMaxSize()
        .verticalScroll
            (rememberScrollState()))
    {

        HeartGraphBox();
        Spacer(modifier = Modifier.height(16.dp))
        HeartRateInfoBox(navController);
        Spacer(modifier = Modifier.height(16.dp))
        HeartRateRecordList(heartList = heartList.value);
        Spacer(modifier = Modifier.height(32.dp))

    }

}