package com.secui.healthone.ui.challenge.challengepage

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ChallengeContentBox(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = ChallengeContentBoxText.challangeContentBoxTitle,
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp, 0.dp)
        );
        Spacer(modifier = Modifier.height(8.dp));
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .horizontalScroll(rememberScrollState())
        ) {

            repeat(8){
                ChallengeContentItem(navController)
            }
        }
    }
}

class ChallengeContentBoxText {
    companion object {
        const val challangeContentBoxTitle = "많이 찾는 운동 컨텐츠"
    }
}