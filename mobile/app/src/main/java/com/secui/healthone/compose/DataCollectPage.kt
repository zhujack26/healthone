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

@Composable
fun DataCollectPage(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            CircleNumber(number = 1, filled = true)
            Spacer(modifier = Modifier.width(8.dp))
            CircleNumber(number = 2, filled = false)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "건강 분석에 필요한 설정",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "1분이면 끝나요",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        // 프로필 사진 추가 컴포넌트
        Spacer(modifier = Modifier.height(48.dp))
//        ProfilePicturePicker()
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

//@Composable
//fun ProfilePicturePicker() {
//    val context = LocalContext.current
//    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.StartActivityForResult()
//    ) { result ->
//        if (result.resultCode == RESULT_OK) {
//            val imageBitmap = result.data?.extras?.get("data") as? Bitmap
//            bitmap.value = imageBitmap
//        }
//    }
//
//    Button(
//        onClick = {
//            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            launcher.launch(intent)
//        },
//        modifier = Modifier
//            .size(128.dp)
//            .padding(8.dp)
//    ) {
//        if (bitmap.value != null) {
//            Image(
//                bitmap = bitmap.value!!.asImageBitmap(),
//                contentDescription = "Profile Picture",
//                modifier = Modifier
//                    .size(128.dp)
//                    .clip(CircleShape)
//            )
//        } else {
//            Image(
//                painter = painterResource(R.drawable.login_run),
//                contentDescription = "Profile Picture",
//                modifier = Modifier
//                    .size(128.dp)
//                    .clip(CircleShape)
//            )
//        }
//    }
//}
