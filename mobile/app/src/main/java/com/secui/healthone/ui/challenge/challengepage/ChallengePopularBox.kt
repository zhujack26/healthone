package com.secui.healthone.ui.challenge.challengepage

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.secui.healthone.ui.common.AppColors

@Composable
fun ChallengePopularBox(
    navController: NavHostController,
    modifier: Modifier = Modifier
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

            repeat(8){
                ChallengePopularItem(navController);
            }
        }
    }

}

class ChallengePopularBoxText {
    companion object {
        const val challangePopularBoxTitle = "인기 있는 운동 챌린지"
    }
}