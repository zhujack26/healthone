package com.secui.healthone.ui.datacollectpage

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.secui.healthone.constant.AppColors
import com.secui.healthone.ui.datacollectpage.ImageUri.saveImageUri

@Composable
fun PhotoPicker() {
    val context = LocalContext.current
    var photoUri: Uri? by remember { mutableStateOf(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        uri?.let {  // null 검사
            photoUri = it
            saveImageUri(context, it) // null이 아닐 경우에만 저장
        }
    }
    val imageColor = AppColors.white
    Spacer(modifier = Modifier.width(64.dp))

    if (photoUri != null) {
        val painter = rememberAsyncImagePainter(
            ImageRequest
                .Builder(LocalContext.current)
                .data(data = photoUri)
                .build()
        )
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .padding(5.dp)
                .size(96.dp)
                .background(imageColor)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        )
    } else {
        Button(
            onClick = {
                launcher.launch(
                    PickVisualMediaRequest(
                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                )
                )
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = imageColor),
            modifier = Modifier
                .padding(5.dp)
                .size(96.dp),
            shape = CircleShape,
        ) {
            Text("추가하기")
        }
    }
}
