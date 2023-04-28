package com.secui.healthone.ui.challenge.challengepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.R
import com.secui.healthone.ui.common.AppColors

@Composable
fun ChallengeUserRewardBox(
    modifier: Modifier = Modifier
){

    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .background(AppColors.white)
            .padding(16.dp)
            .clickable { }
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
        ) {

            Row(modifier= Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier= Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(text = ChallengeUserRewardBoxText.currentText,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Right
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = ChallengeUserRewardBoxText.currentRewardValue,
                        fontSize = 36.sp,
                        color = AppColors.red300,
                        textAlign = TextAlign.Right
                    )
                }
                Spacer(modifier = Modifier.width(24.dp))
                Image(painter = painterResource(id = R.drawable.ic_mark),
                    contentDescription = "도장 이미지",
                    modifier = Modifier
                        .width(72.dp)
                        .height(72.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = ChallengeUserRewardBoxText.userRewardBottomText,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center
            )
        }
    }
}

class ChallengeUserRewardBoxText {
    companion object {
        const val currentText = "지금까지"
        const val currentRewardValue = "22"
        const val userRewardBottomText = "개의 챌린지를 완료하셨어요"
    }
}