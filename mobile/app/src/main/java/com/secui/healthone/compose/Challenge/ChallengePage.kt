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
import androidx.navigation.NavHostController
import com.secui.healthone.ui.challenge.challengepage.ChallengeContentBox
import com.secui.healthone.ui.challenge.challengepage.ChallengePopularBox
import com.secui.healthone.ui.challenge.challengepage.ChallengeTotalScoreBox
import com.secui.healthone.ui.challenge.challengepage.ChallengeUserBadgeBox
import com.secui.healthone.ui.challenge.challengepage.ChallengeUserRewardBox

@Composable
fun ChallengePage(
    navController: NavHostController,
    modifier: Modifier=Modifier)
{
    Column(modifier= Modifier
        .fillMaxSize()
        .verticalScroll
            (rememberScrollState())
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        ChallengePopularBox(navController)
        ChallengeContentBox(navController)
        ChallengeTotalScoreBox()
        ChallengeUserRewardBox();
        ChallengeUserBadgeBox();

    }
}