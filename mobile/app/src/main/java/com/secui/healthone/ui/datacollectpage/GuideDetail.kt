package com.secui.healthone.ui.datacollectpage

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.secui.healthone.R
import com.secui.healthone.util.PageRoutes
import kotlinx.coroutines.delay

@Composable
fun GuideDetail(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        var firstText by remember { mutableStateOf(false) }
        var secondText by remember { mutableStateOf(false) }
        var thirdText by remember { mutableStateOf(false) }
        var fourthText by remember { mutableStateOf(false) }
        var fifthText by remember { mutableStateOf(false) }

        LaunchedEffect(true) {
            firstText = true
            delay(500)
            secondText = true
            delay(500)
            thirdText = true
            delay(500)
            fourthText = true
            delay(500)
            fifthText = true
        }
        Spacer(modifier = Modifier.height(48.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .animateContentSize()
                .fillMaxWidth()
        ) {
            if (firstText) {
                Text(
                    text = "맞춤형 건강 관리",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            if (secondText) {
                Text(
                    text = "정보 제공",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(48.dp))
        Column() {
            if (thirdText) {
                Row() {
                    Image(
                        painter = painterResource(id = R.drawable.ic_check),
                        contentDescription = "check",
                        modifier = Modifier
                            .width(16.dp)
                            .height(16.dp),
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "매일의 건강 데이터 정리",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            if (fourthText) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_check),
                        contentDescription = "check",
                        modifier = Modifier
                            .width(16.dp)
                            .height(16.dp),
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "건강 상태 개선",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
                Spacer(modifier = Modifier.height(48.dp))
            if (fifthText) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .width(280.dp)
                            .height(56.dp)
                            .clip(RoundedCornerShape(32.dp))
                            .background(colorResource(id = R.color.mono200))
                            .clickable { navController.navigate(PageRoutes.OverView.route) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "시작하기",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.mono400)
                        )
                    }
                }
            }
        }
    }
}

suspend fun animateText(showText: MutableState<Boolean>, duration: Long) {
    val anim = Animatable(0f)
    anim.animateTo(1f, animationSpec = tween(durationMillis = duration.toInt()))
    showText.value = true
}