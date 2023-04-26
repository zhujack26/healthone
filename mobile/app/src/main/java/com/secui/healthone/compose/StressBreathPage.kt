package com.secui.healthone.compose

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.secui.healthone.R
import com.secui.healthone.ui.stressbreathpage.StressBreathBox
import com.secui.healthone.ui.stressbreathpage.StressBreathCounter
import com.secui.healthone.util.PageRoutes

@Composable
fun StressBreathPage(
    navController: NavHostController,
    modifier: Modifier = Modifier
){


    Column(modifier= Modifier
        .fillMaxSize()
        .padding(16.dp)
        .verticalScroll
            (rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
        ){

        Spacer(modifier = Modifier.height(16.dp))

        // 타이틀
        Text(text = StressBreathPageText.breathGuideText, fontSize = 16.sp);

        Spacer(modifier = Modifier.height(32.dp))

        // 호흡 수 카운터
        StressBreathCounter()

        Spacer(modifier = Modifier.height(32.dp))

        // 샘플 이미지
        Image(painter = rememberImagePainter(StressBreathPageText.sampleImgURL),
            contentDescription = "가이드 이미지",
            modifier = Modifier
                .width(256.dp)
                .height(256.dp),
            contentScale = ContentScale.Crop,
        );

        Spacer(modifier = Modifier.height(32.dp))

        // 호흡 버튼
        Button( colors = ButtonDefaults
            .outlinedButtonColors(
                backgroundColor =
                colorResource(id = R.color.green300)
            ),
            modifier = Modifier
                .width(256.dp)
                .height(48.dp)
            ,
            onClick = {  }) {
            Text(text = StressBreathPageText.breathBtnText,
                fontSize = 20.sp,
                color =
                colorResource(
                id = R.color.white
            ))
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 호흡 수 정보 박스
        StressBreathBox();
    }
}

class StressBreathPageText {
    companion object{
        const val breathGuideText = "호흡은 스트레스 완화에 효과적입니다"
        const val breathBtnText = "호흡"
        const val inhaleText = "들숨"
        const val inhalePeriod = "5초"
        const val exhalationText = "날숨"
        const val exhalationPeriod = "5초"
        const val sampleImgURL = "https://img.freepik.com/free-photo/indian-man-concentrating-raising-hands-outdoors-with-blue-sky-green-tree-branches_1262-12684.jpg"
    }
}