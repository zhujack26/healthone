package com.secui.healthone.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.secui.healthone.ui.challenge.challengepage.ChallengeContentBox
import com.secui.healthone.ui.challenge.challengepage.ChallengePopularBox
import com.secui.healthone.ui.challenge.challengepage.ChallengeRankItem
import com.secui.healthone.ui.challenge.challengepage.ChallengeTotalScoreBox
import com.secui.healthone.ui.challenge.challengepage.ChallengeUserBadgeBox
import com.secui.healthone.ui.challenge.challengepage.ChallengeUserRewardBox
import com.secui.healthone.ui.challenge.populardetail.ToolsInfoDialog
import com.secui.healthone.ui.common.AppColors

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
        Spacer(modifier = Modifier.height(32.dp))
        ChallengeContentBox(navController)
        Spacer(modifier = Modifier.height(16.dp))
        ChallengeRankItem(navController)

    }
}