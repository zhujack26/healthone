package com.secui.healthone.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.ui.datacollectpage.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Alignment
import androidx.navigation.NavController

@Composable
fun DataCollectFirstPage(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        item {
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
            val birthDate = remember { mutableStateOf("") }
            BirthDate(
                value = birthDate.value,
                onValueChange = { newDate ->
                    birthDate.value = newDate
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            // 신장 컴포넌트
            Height()
            Spacer(modifier = Modifier.height(16.dp))
            // 체중 컴포넌트
            Weight()
            Spacer(modifier = Modifier.height(32.dp))
            // 다음 버튼 컴포넌트
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                NextButton(navController)
            }
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}
@Composable
fun DataCollectSecondPage() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        item {
            // 인덱스 컴포넌트
            Row {
                Index(number = 1, filled = false)
                Spacer(modifier = Modifier.width(16.dp))
                Index(number = 2, filled = true)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "목표를 설정해보세요",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(32.dp))
            // 목표 걸음수 컴포넌트
            StepGoal()
            Spacer(modifier = Modifier.height(16.dp))

            //
            SleepGoal()
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}