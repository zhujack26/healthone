package com.secui.healthone.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.secui.healthone.constant.AppColors
import com.secui.healthone.ui.datacollectpage.*
import com.secui.healthone.ui.mypage.*

@Composable
fun MyPage(navController: NavController) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(16.dp))
                ProfilePhoto()
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = ImageUri.getNicknameFromPrefs(context),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.black
                    )
                Spacer(modifier = Modifier.height(24.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    WeeklyAnalysis()
                    Spacer(modifier = Modifier.height(16.dp))
                    Records()
                }
            }
        }
    }
}