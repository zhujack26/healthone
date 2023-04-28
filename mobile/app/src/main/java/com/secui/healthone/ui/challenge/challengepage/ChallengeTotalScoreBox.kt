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
fun ChallengeTotalScoreBox(
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
            Text(text = ChallengeTotalScoreBoxText.scoreHeadText,
                modifier= Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = AppColors.black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier= Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = ChallengeTotalScoreBoxText.scoreCountValue,
                    fontSize = 28.sp,
                    color = AppColors.orange300
                )
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_fire),
                    contentDescription = "불꽃 이미지",
                    modifier = Modifier.width(32.dp).height(32.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = ChallengeTotalScoreBoxText.scoreBottomText,
                modifier= Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = AppColors.black
            )

        }

    }


}

class ChallengeTotalScoreBoxText {
    companion object {
        const val scoreHeadText = "전체 챌린지에서 현재"
        const val scoreCountValue = "3458469"
        const val scoreBottomText = "명이 참여해주고 있어요"
    }
}