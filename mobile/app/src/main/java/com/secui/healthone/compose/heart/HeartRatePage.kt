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
import com.secui.healthone.constant.HealthOnePage
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

    // 타이틀 값 수정
    HealthOnePage.pageTitle.value = "심박수"

    val heartList = HeartRateViewModel.heartRateList;
    // heartRate 세팅
    val mOwner = LocalLifecycleOwner.current
    val repository = HeartRateRepository()
    val viewModel = HeartRateViewModel(repository)

    viewModel.getHeartRateList();
    viewModel.heartListResponse.observe(mOwner, Observer{
        heartList.value = it;
    })
    HeartRateViewModel.heartRateList.value = heartList.value;

    Column(modifier= Modifier
        .fillMaxSize()
        .verticalScroll
            (rememberScrollState()))
    {

        HeartGraphBox(heartList = heartList.value);
        Spacer(modifier = Modifier.height(16.dp))
        HeartRateInfoBox(navController);
        Spacer(modifier = Modifier.height(16.dp))
        if(heartList.value.size>0){
            HeartRateRecordList(heartList = heartList.value);
            Spacer(modifier = Modifier.height(32.dp))
        }
    }

}