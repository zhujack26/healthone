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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.secui.healthone.R

@Composable
fun PhotoPicker() {
    var photoUri: Uri? by remember { mutableStateOf(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        photoUri = uri
    }
    val imageColor = colorResource(id = R.color.white)

    Row(verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "프로필 사진",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
        )
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
        }
        else {
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
}
