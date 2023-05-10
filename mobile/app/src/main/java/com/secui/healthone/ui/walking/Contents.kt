package com.secui.healthone.ui.walking

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.secui.healthone.data.Video

@Composable
fun Contents(video: Video, isFirst: Boolean = true) {
    val context = LocalContext.current
    Card(

        modifier = Modifier
            .size(180.dp)
            .padding(
                if (isFirst) 0.dp else 20.dp, // 첫 번째 카드는 왼쪽에 붙이기
                top = 20.dp,
                end = 20.dp,
                bottom = 20.dp
            )
            .clip(RoundedCornerShape(32.dp))
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=${video.id}"))
                context.startActivity(intent)
            },
        elevation = 4.dp,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberImagePainter(video.thumbnailUrl),
                contentDescription = "Thumbnail of ${video.title}",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}