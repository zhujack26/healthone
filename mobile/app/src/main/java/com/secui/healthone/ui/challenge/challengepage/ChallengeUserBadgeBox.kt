package com.secui.healthone.ui.challenge.challengepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.R
import com.secui.healthone.constant.AppColors

@Composable
fun ChallengeUserBadgeBox(
    modifier: Modifier = Modifier
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
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
                .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // 최근 달성한 업적
                Text(text = ChallengeUserBadgeBoxText.badgeTitle,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_badge),
                    contentDescription = "배지 이미지",
                    modifier = Modifier
                        .width(72.dp)
                        .height(72.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(16.dp))

                // bottomText
                Text(text = ChallengeUserBadgeBoxText.badgeName,
                    fontSize = 16.sp
                )
            }


        }
    }
}

class ChallengeUserBadgeBoxText {
    companion object {
        const val badgeTitle = "최근 달성한 업적"
        const val badgeName = "성실한 헬스 어린이"
    }
}