package com.secui.healthone.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.R

@Composable
fun LoginPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFDFDFD)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .weight(0.67f)
                .fillMaxWidth()
        ) {
            val animationPainter: Painter = painterResource(R.drawable.run)
            Image(
                painter = animationPainter,
                contentDescription = "Animation",
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            text = "간편하게 가입하고 로그인해요",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )

        val googleSignInPainter: Painter = painterResource(R.drawable.google_logo)
        Image(
            painter = googleSignInPainter,
            contentDescription = "Google Login",
            modifier = Modifier
                .padding(16.dp)
                .size(48.dp)
//                .clickable
        )

        Text(
            text = "로그인시 이용약관과 개인정보 보호호방침에 동의하게 됩니다.",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )
    }
}