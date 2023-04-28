package com.secui.healthone.ui.challenge.challengepage

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.secui.healthone.ui.common.AppColors

@Composable
fun ChallengeRankItem(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Do it! Challenges",
        fontSize = 36.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        color = AppColors.red300,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(16.dp))
    ChallengeTotalScoreBox()
    ChallengeUserRewardBox();
    ChallengeUserBadgeBox();
}