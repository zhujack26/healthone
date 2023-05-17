package com.secui.healthone.compose.healthstatus

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.secui.healthone.constant.AppColors
import com.secui.healthone.ui.HealthStatus.HelpButton
import com.secui.healthone.ui.HealthStatus.onButtonClick
import com.secui.healthone.viewmodel.HealthStatusViewModel

@Composable
fun HealthHelpPage(navController: NavHostController) {
    val viewModel = remember { HealthStatusViewModel() }
    viewModel.fetchHealthAdvice()
    val HealthAdvices = viewModel.healthAdvice.value  // Here, use the 'value' property.
    val healthRecords = viewModel.healthRecords
    val record = if(healthRecords.isNotEmpty()) { healthRecords.last() } else { null }
    // 선택된 버튼을 저장하는 변수를 추가합니다.
    val selectedButton = remember { mutableStateOf("체중") }
    var dangerousText = ""
    if (HealthAdvices != null) {
        val bloodPipe = if (HealthAdvices.tg == "위험" || HealthAdvices.hdlCholesterol == "위험") {
            "위험"
        } else if (HealthAdvices.tg == "주의" || HealthAdvices.hdlCholesterol == "주의") {
            "주의"
        } else {
            "정상"
        }
        val weightValue = if (HealthAdvices.weight == "정상")
        {"정상"}
        else if(HealthAdvices.weight == "과체중")
        {"주의"}
        else{"위험"}

        val dangerValues = listOf(
            if (weightValue == "위험"|| weightValue =="주의") "체중" else "",
            if (HealthAdvices.bmi == "위험" || HealthAdvices.bmi =="주의") "BMI" else "",
            if (HealthAdvices.fatPercentage == "위험" || HealthAdvices.fatPercentage =="주의") "체지방" else "",
            if (HealthAdvices.skeletalMuscleMass == "위험" || HealthAdvices.skeletalMuscleMass =="주의") "골격근" else "",
            if (HealthAdvices.waistMeasurement == "위험" || HealthAdvices.waistMeasurement =="주의") "복부비만" else "",
            if (bloodPipe == "위험" || bloodPipe == "주의") "혈관" else "",
            if (HealthAdvices.bloodPressure == "위험" || HealthAdvices.bloodPressure =="주의") "혈압" else "",
            if (HealthAdvices.fbg == "위험" || HealthAdvices.fbg =="주의") "공복혈당" else ""
        ).filter { it.isNotEmpty() }

        Column(modifier = Modifier.fillMaxWidth()) {
            if(dangerValues.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(AppColors.red50),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        dangerValues.forEach { value ->
                            dangerousText += value + " "
                        }
                        Text(
                            text = "\uD83D\uDCE2 ${dangerousText} \n 관리가 필요합니다",
                            color = AppColors.red200,
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp
                        )
                    }
                }
            }
            else{
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(AppColors.green50),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        dangerValues.forEach { value ->
                            dangerousText += value + " "
                        }
                        Text(
                            text = "\uD83D\uDCE2  건강관리가 잘 되고있습니다",
                            color = AppColors.green200,
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp
                        )
                    }
                }
            }
            val scrollState = rememberScrollState()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(scrollState)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                HelpButton(
                    text = "체중",
                    state = weightValue,
                    isSelected = selectedButton.value == "체중",
                    onClick = { selectedButton.value = "체중"; onButtonClick("체중") })
                HelpButton(
                    text = "BMI",
                    state = HealthAdvices.bmi,
                    isSelected = selectedButton.value == "BMI",
                    onClick = { selectedButton.value = "BMI"; onButtonClick("BMI") })
                HelpButton(
                    text = "체지방",
                    state = HealthAdvices.fatPercentage,
                    isSelected = selectedButton.value == "체지방",
                    onClick = { selectedButton.value = "체지방"; onButtonClick("체지방") })
                HelpButton(
                    text = "골격근",
                    state = HealthAdvices.skeletalMuscleMass,
                    isSelected = selectedButton.value == "골격근",
                    onClick = { selectedButton.value = "골격근"; onButtonClick("골격근") })
                HelpButton(
                    text = "복부비만",
                    state = HealthAdvices.waistMeasurement,
                    isSelected = selectedButton.value == "복부비만",
                    onClick = { selectedButton.value = "복부비만"; onButtonClick("복부비만") })
                HelpButton(
                    text = "혈관",
                    state = bloodPipe,
                    isSelected = selectedButton.value == "혈관",
                    onClick = { selectedButton.value = "혈관"; onButtonClick("혈관") })
                HelpButton(
                    text = "혈압",
                    state = HealthAdvices.bloodPressure,
                    isSelected = selectedButton.value == "혈압",
                    onClick = { selectedButton.value = "혈압"; onButtonClick("혈압") })
                HelpButton(
                    text = "공복혈당",
                    HealthAdvices.fbg,
                    isSelected = selectedButton.value == "공복혈당",
                    onClick = { selectedButton.value = "공복혈당"; onButtonClick("공복혈당") })
            }

            if (HealthAdvices != null) {
                val displayValue = when (selectedButton.value) {
                    "체중" -> record?.weight  // null 검사 추가
                    "BMI" -> record?.bmi  // null 검사 추가
                    "체지방" -> record?.bodyFatPercentage  // null 검사 추가
                    "골격근" -> record?.skeletalMuscleMass  // null 검사 추가
                    "복부비만" -> record?.waistMeasurement  // null 검사 추가
                    "혈관" -> "${record?.tg}, ${record?.hdlCholesterol}"  // null 검사 추가
                    "혈압" -> "${record?.lowBloodPressure}${record?.highBloodPressure}"  // null 검사 추가
                    "공복혈당" -> record?.fbg  // null 검사 추가
                    else -> ""
                }

                Text(
                    text = displayValue.toString(),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    } else{
        Text("기록이 없습니다")
    }
}

