package com.secui.healthone.compose.healthstatus

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.secui.healthone.ui.HealthStatus.HealthStatusItem
import com.secui.healthone.constant.AppColors
import com.secui.healthone.constant.HealthOnePage
import com.secui.healthone.ui.mealplanpage.DateComponent
import com.secui.healthone.constant.PageRoutes
import com.secui.healthone.data.HealthStatus.HealthStatus
import com.secui.healthone.viewmodel.HealthStatusViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun HealthStatusPage(navController: NavHostController) {
    HealthOnePage.pageTitle.value="건강상태"
    val initialDate = Calendar.getInstance()
    val selectedDate = remember { mutableStateOf(initialDate) }
    val viewModel = remember { HealthStatusViewModel() }
    val selectedDateString by remember {
        derivedStateOf {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            sdf.format(selectedDate.value.time)
        }
    }

    LaunchedEffect(selectedDateString) {
        viewModel.fetchHealthRecords(selectedDateString)
    }
    val defaultHealthStatus = HealthStatus(
        no = null,
        userNo = 0,
        createTime = "",
        height = null,
        weight = null,
        bmi = null,
        bodyFatPercentage = null,
        skeletalMuscleMass = null,
        waistMeasurement = null,
        fbg = null,
        hdlCholesterol = null,
        tg = null,
        lowBloodPressure = null,
        highBloodPressure = null
    )
    val healthRecords = if(viewModel.healthRecords.isEmpty()) {
        listOf(defaultHealthStatus)
    } else {
        viewModel.healthRecords
    }
    val record = healthRecords.last()
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
                        "나의 건강정보", modifier = Modifier.align(Alignment.CenterVertically),
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    )
                    Button(
                        onClick = { navController.navigate(PageRoutes.HealthHelp.route) },
                        modifier = Modifier
                            .width(180.dp)
                            .padding(8.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.green200)
                    ) {
                        Text(
                            text = "건강 조언 보기",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
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
                        HealthStatusItem(
                            key = "키",
                            value = (record.height?.toString() ?: "") + "cm")
                        HealthStatusItem(
                            key = "체중",
                            value = (record.weight?.toString() ?: "") + "kg")
                        HealthStatusItem(
                            key = "BMI",
                            value = (record.bmi?.toString() ?: "") + "bmi")
                        HealthStatusItem(
                            key = "체지방률",
                            value = (record.bodyFatPercentage?.toString() ?: "") + "%")
                        HealthStatusItem(
                            key = "골격근량",
                            value = (record.skeletalMuscleMass?.toString() ?: "") + "kg")
                        HealthStatusItem(
                            key = "허리둘레",
                            value = (record.waistMeasurement?.toString() ?: "")+"cm")
                        HealthStatusItem(
                            key = "혈압",
                            value = (record.highBloodPressure?.toString()?: "") + "/" + (record.lowBloodPressure?.toString()?: "     ")+"mmHg")
                        HealthStatusItem(
                            key = "공복혈당",
                            value = (record.fbg?.toString() ?: "")+"mg/dL")
                        HealthStatusItem(
                            key = "HDL콜레스테롤",
                            value = (record.hdlCholesterol?.toString() ?: "")+"mg/dL")
                        HealthStatusItem(
                            key = "중성지방",
                            value = (record.tg?.toString() ?: "")+"mg/dL")
                }
            }
        }
            item {
                Button(
                    onClick = { navController.navigate(PageRoutes.HealthStatusInput.route) },
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
