package com.secui.healthone.ui.datacollectpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.secui.healthone.constant.AppColors
import com.secui.healthone.ui.datacollectpage.ImageUri.loadImageUri

@Composable
fun ProfilePhoto() {
    val context = LocalContext.current
    val photoUri = remember { mutableStateOf(loadImageUri(context)) }

    if (photoUri.value != null) {
        val painter = rememberAsyncImagePainter(
            ImageRequest
                .Builder(context)
                .data(data = photoUri.value)
                .build()
        )
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .padding(5.dp)
                .size(96.dp)
                .background(AppColors.white)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}