package com.secui.healthone.compose

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.R


//

import android.net.Uri
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign

@Composable
fun DataCollectPage(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        // 인덱스 컴포넌트
        Row {
            CircleNumber(number = 1, filled = true)
            Spacer(modifier = Modifier.width(16.dp))
            CircleNumber(number = 2, filled = false)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "건강 분석에 필요한 설정",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Left
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "1분이면 끝나요",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Left
        )
        // 프로필 사진 추가 컴포넌트
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "프로필 사진"
        )
        PhotoPickerDemoScreen()
        Spacer(modifier = Modifier.height(16.dp))

    }
}

@Composable
fun CircleNumber(number: Int, filled: Boolean) {
    val backgroundColor = if (filled) colorResource(id = R.color.black) else colorResource(id = R.color.mono600)
    val textColor = if (filled) colorResource(id = R.color.white) else colorResource(id = R.color.black)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(48.dp)
            .background(backgroundColor, CircleShape),
    ) {
        Text(
            text = number.toString(),
            color = textColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PhotoPickerDemoScreen() {
    //The URI of the photo that the user has picked
    var photoUri: Uri? by remember { mutableStateOf(null) }

    //The launcher we will use for the PickVisualMedia contract.
    //When .launch()ed, this will display the photo picker.
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        //When the user has selected a photo, its URI is returned here
        photoUri = uri
    }


    Column {
        Button(
            onClick = {
                //On button press, launch the photo picker
                launcher.launch(PickVisualMediaRequest(
                    //Here we request only photos. Change this to .ImageAndVideo if you want videos too.
                    //Or use .VideoOnly if you only want videos.
                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                ))
            }
        ) {
            Text("추가하기")
        }

        if (photoUri != null) {
            //Use Coil to display the selected image
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
                    .fillMaxWidth()
                    .border(6.0.dp, Color.Gray),
                contentScale = ContentScale.Crop
            )
        }
    }
}

