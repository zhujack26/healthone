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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.secui.healthone.data.ChallengeInfo

@Composable
fun ChallengePopularBox(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    challengeList:MutableList<ChallengeInfo>
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = ChallengePopularBoxText.challangePopularBoxTitle,
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp, 0.dp),
            fontWeight = FontWeight.SemiBold
        );
        Spacer(modifier = Modifier.height(8.dp));
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .horizontalScroll(rememberScrollState())
        ) {
            var idx = 0;
            repeat(challengeList.size){
                ChallengePopularItem(navController, idx=idx, challengeInfo = challengeList[idx++]);
            }
        }
    }

}

class ChallengePopularBoxText {
    companion object {
        const val challangePopularBoxTitle = "인기 있는 운동 챌린지"
    }
}