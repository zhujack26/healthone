package com.secui.healthone.ui.challenge.challengepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.secui.healthone.R

@Composable
fun ChallengeContentItem(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .shadow(1.dp, RectangleShape, ambientColor = colorResource(id = R.color.black)),
    )
    {
        // 이미지
        Image(painter =
        rememberImagePainter(ChallengeContentItemText.contentImgUrl),
            contentDescription = "컨텐츠 이미지",
            modifier = Modifier
                .width(204.dp)
                .height(136.dp),
            contentScale = ContentScale.Crop
        )
    }
}

class ChallengeContentItemText {
    companion object {
        const val contentImgUrl = "https://i.ytimg.com/vi/gC_L9qAHVJ8/maxresdefault.jpg"
    }
}