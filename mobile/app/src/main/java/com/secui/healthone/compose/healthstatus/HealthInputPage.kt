package com.secui.healthone.compose.healthstatus

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.secui.healthone.ui.HealthStatus.HealthInputItem
import com.secui.healthone.ui.common.AppColors
import com.secui.healthone.ui.mealplanpage.DateComponent
import com.secui.healthone.util.PageRoutes

@Composable
fun HealthInputPage(navController: NavHostController) {
    val heightValueState = remember { mutableStateOf("") }
    val weightValueState = remember { mutableStateOf("") }
    val bodyFatValueState = remember { mutableStateOf("") }
    val muscleMassValueState = remember { mutableStateOf("") }
    val waistCircumferenceValueState = remember { mutableStateOf("") }
    val bloodPressureValueState = remember { mutableStateOf("") }
    val fastingBloodGlucoseValueState = remember { mutableStateOf("") }
    val hdlCholesterolValueState = remember { mutableStateOf("") }
    val triglyceridesValueState = remember { mutableStateOf("") }
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
                    DateComponent()
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
                    HealthInputItem(key = "혈압", value = bloodPressureValueState, unit = "mmHg")
                    HealthInputItem(key = "공복혈당", value = fastingBloodGlucoseValueState, unit = "mg/dL")
                    HealthInputItem(key = "HDL콜레스테롤", value = hdlCholesterolValueState, unit = "mg/dL")
                    HealthInputItem(key = "중성지방", value = triglyceridesValueState, unit = "mg/dL")

                }
            }
        }
        item {
            Button(
                onClick = {  navController.navigate(PageRoutes.HealthStatusInput.route) },
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