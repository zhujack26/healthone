package com.secui.healthone.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.secui.healthone.ui.alert.AlertItem
import com.secui.healthone.viewmodel.AlertViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import com.secui.healthone.util.AlertViewModelFactory
import com.secui.healthone.util.PreferencesManager


@Composable
fun AlertPage(
    navController: NavHostController,
    alertViewModel: AlertViewModel = viewModel(factory = AlertViewModelFactory(LocalContext.current, PreferencesManager(LocalContext.current))),
    modifier: Modifier=Modifier){
    alertViewModel.setAlert()
    alertViewModel.setSleepAlert()
    val alert = alertViewModel.alert.observeAsState(initial = AlertViewModel.AlertItemText("", "", "", "")).value
    Column(modifier= Modifier
        .fillMaxSize()
        .verticalScroll
            (rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        repeat(1) {
            AlertItem(navController, alert)
        }
    }
}