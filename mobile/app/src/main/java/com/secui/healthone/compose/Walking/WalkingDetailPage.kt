package com.secui.healthone.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.secui.healthone.ui.walking.*
import com.secui.healthone.util.FitWalkManager


@Composable
fun WalkingDetailPage(navController: NavController, account: GoogleSignInAccount) {
    val hourlyStepsData = FitWalkManager.readHourlyWalkSteps(navController.context, account)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        WalkingGraph(navController, hourlyStepsData.value)
    }
}