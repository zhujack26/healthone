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
import com.secui.healthone.ui.datacollectpage.*


//

import android.net.Uri
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign

@Composable
fun DataCollectPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        // 인덱스 컴포넌트
        Row {
            Index(number = 1, filled = true)
            Spacer(modifier = Modifier.width(16.dp))
            Index(number = 2, filled = false)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "건강 분석에 필요한 설정",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "1분이면 끝나요",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(32.dp))

        // 프로필 사진 추가 컴포넌트
        PhotoPicker()
        Spacer(modifier = Modifier.height(16.dp))
        // 성별 컴포넌트
        GenderSelection()
        Spacer(modifier = Modifier.height(16.dp))
        // 닉네임 컴포넌트
        NicknameInput()
        Spacer(modifier = Modifier.height(16.dp))

        // 생년월일 컴포넌트

        // 신장 컴포넌트

        // 체중 컴포넌트

        // 신장 컴포넌트

        // 체중 컴포넌트

        // 다음 버튼 컴포넌트
    }
}