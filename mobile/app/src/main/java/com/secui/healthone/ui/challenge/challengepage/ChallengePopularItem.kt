package com.secui.healthone.ui.challenge.challengepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.secui.healthone.R
import com.secui.healthone.ui.common.AppColors

@Composable
fun ChallengePopularItem(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .shadow(1.dp, RectangleShape, ambientColor = AppColors.black),
    )
    {
        // 이미지
        Image(painter =
        rememberImagePainter(ChallengePopularItemText.challangeImgUrl),
            contentDescription = "챌린지 이미지",
            modifier = Modifier
                .width(228.dp)
                .height(128.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(4.dp))
        // 타이틀
        Text(text = ChallengePopularItemText.ChallangePopularTitle,
            fontSize = 16.sp,
            color = colorResource(id = R.color.black),
            modifier = Modifier.padding(8.dp, 4.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        // 기간
        Text(text = ChallengePopularItemText.ChallangePopularPeriod,
            fontSize = 14.sp,
            color = colorResource(id = R.color.mono700),
            modifier = Modifier.padding(8.dp)
        )
    }
}

class ChallengePopularItemText {
    companion object {
        const val challangeImgUrl = "https://health.chosun.com/site/data/img_dir/2019/04/29/2019042900839_0.jpg"
        const val ChallangePopularTitle = "특색 있는 운동, 요가 챌린지"
        const val ChallangePopularPeriod = "2주"
    }
}