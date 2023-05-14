package com.secui.healthone.compose.healthstatus

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.secui.healthone.ui.HealthStatus.HealthInputItem
import com.secui.healthone.constant.AppColors
import com.secui.healthone.ui.mealplanpage.DateComponent
import com.secui.healthone.constant.PageRoutes
import com.secui.healthone.data.HealthStatus.HealthStatus
import com.secui.healthone.data.HealthStatus.SendHealthStatus
import com.secui.healthone.viewmodel.HealthStatusViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun HealthInputPage(navController: NavHostController) {
    val heightValueState = remember { mutableStateOf<Int?>(null) }
    val weightValueState = remember { mutableStateOf<Int?>(null) }
    val bodyFatValueState = remember { mutableStateOf<Int?>(null) }
    val muscleMassValueState = remember { mutableStateOf<Int?>(null) }
    val waistCircumferenceValueState = remember { mutableStateOf<Int?>(null) }
    val highbloodPressureValueState = remember { mutableStateOf<Int?>(null) }
    val lowbloodPressureValueState = remember { mutableStateOf<Int?>(null) }
    val fastingBloodGlucoseValueState = remember { mutableStateOf<Int?>(null) }
    val hdlCholesterolValueState = remember { mutableStateOf<Int?>(null) }
    val triglyceridesValueState = remember { mutableStateOf<Int?>(null) }
    val initialDate = Calendar.getInstance()
    val selectedDate = remember { mutableStateOf(initialDate) }
    val selectedDateString by remember {
        derivedStateOf {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            sdf.format(selectedDate.value.time)
        }
    }
    val selectedDateStringForm = selectedDateString + "T00:00:00.000Z"
    val viewModel: HealthStatusViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    val bmiValue: Int = if (heightValueState.value != null && weightValueState.value != null) {
        (weightValueState.value!! / ((heightValueState.value!! / 100.0f) * (heightValueState.value!! / 100.0f))).toInt()
    } else {
        0  // Or any other default value you prefer
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Card(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DateComponent(
                        selectedDate = selectedDate,
                        onDateChanged = { newDate ->
                            selectedDate.value = newDate
                        }
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Card(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "건강정보 입력", modifier = Modifier.align(Alignment.CenterVertically),
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
        item {
            Card(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    HealthInputItem(key = "키", value = heightValueState, unit = "cm")
                    HealthInputItem(key = "체중", value = weightValueState, unit = "Kg")
                    HealthInputItem(key = "체지방률", value = bodyFatValueState, unit = "%")
                    HealthInputItem(key = "골격근량", value = muscleMassValueState, unit = "Kg")
                    HealthInputItem(key = "허리둘레", value = waistCircumferenceValueState, unit = "cm")
                    HealthInputItem(key = "수축기혈압", value = highbloodPressureValueState, unit = "mmHg")
                    HealthInputItem(key = "이완기혈압", value = lowbloodPressureValueState, unit = "mmHg")
                    HealthInputItem(key = "공복혈당", value = fastingBloodGlucoseValueState, unit = "mg/dL")
                    HealthInputItem(key = "HDL콜레스테롤", value = hdlCholesterolValueState, unit = "mg/dL")
                    HealthInputItem(key = "중성지방", value = triglyceridesValueState, unit = "mg/dL")

                }
            }
        }
        item {
                Button(
                    onClick = {
                        val healthStatus = SendHealthStatus(
                            userNo = 1,
                            createTime = selectedDateStringForm,
                            height = heightValueState.value,
                            weight = weightValueState.value,
                            bmi = bmiValue,
                            bodyFatPercentage = bodyFatValueState.value,
                            skeletalMuscleMass = muscleMassValueState.value,
                            waistMeasurement = waistCircumferenceValueState.value,
                            fbg = fastingBloodGlucoseValueState.value,
                            hdlCholesterol = hdlCholesterolValueState.value,
                            tg = triglyceridesValueState.value,
                            lowBloodPressure = lowbloodPressureValueState.value,
                            highBloodPressure = highbloodPressureValueState.value
                        )
                        coroutineScope.launch {
                            viewModel.addHealthStatus(healthStatus)
                            Log.d("헬스정보", "왔다")
                        }
                        navController.navigate(PageRoutes.HealthStatus.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.green200)
                ) {
                    Text(
                        text = "건강정보 최신화",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
    }
}