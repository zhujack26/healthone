package com.secui.healthone.ui.challenge.challengepage

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.secui.healthone.constant.AppColors
import com.secui.healthone.data.Video

@Composable
fun ChallengeContentItem(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    video: Video
){

    val context = LocalContext.current;

    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .background(AppColors.white)
            .padding(8.dp)
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=${video.id}"))
                context.startActivity(intent)
            }
    ) {
        Column(
            modifier = Modifier
                .width(204.dp)
                .wrapContentHeight()
                .shadow(1.dp, RectangleShape, ambientColor = AppColors.black),
        )
        {
            // 이미지
            Image(painter =
            rememberImagePainter(video.thumbnailUrl),
                contentDescription = "컨텐츠 이미지",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(136.dp),
                contentScale = ContentScale.Crop
            )
        }

    }
}

class ChallengeContentItemText {
    companion object {
        const val contentImgUrl = "https://i.ytimg.com/vi/gC_L9qAHVJ8/maxresdefault.jpg"
    }
}