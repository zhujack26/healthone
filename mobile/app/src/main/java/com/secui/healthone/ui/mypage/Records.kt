package com.secui.healthone.ui.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.secui.healthone.R
import com.secui.healthone.api.fit.FitAPIConfig
import com.secui.healthone.api.fit.FitWalkManager
import com.secui.healthone.constant.AppColors

@Composable
fun Records(
    totalTime: String = "1546분",
    totalCalories: String = "9954kcal",
    bestTime: String = "76분",
    bestCalories: String = "695kcal"
) {
    val context = LocalContext.current
    val account = FitAPIConfig.getGoogleSignInAccount(context = context)
    // 최고 걸음수
    val highestStepsValue: State<Int> = remember {
        FitWalkManager.readMaxDailySteps(context, account)
    }
    val highestSteps = highestStepsValue.value

    // 총 걸음수
    val totalStepsValue: State<Int> = remember {
        FitWalkManager.readTotalSteps(context, account)
    }
    val totalSteps = totalStepsValue.value

    val textColor = AppColors.black
    Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "총 기록",
                    color = textColor
                )
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    RecordItem(
                        icon = painterResource(R.drawable.ic_walking_svg),
                        value = totalSteps.toString(),
                        label = "걸음수",
                    )
                    RecordItem(
                        icon = painterResource(R.drawable.ic_time),
                        value = totalTime,
                        label = "운동 시간"
                    )
                    RecordItem(
                        icon = painterResource(R.drawable.ic_fire),
                        value = totalCalories,
                        label = "칼로리 소모량"
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "최고 기록",
                    color = textColor
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    RecordItem(
                        icon = painterResource(R.drawable.ic_walking_svg),
                        value = highestSteps.toString(),
                        label = "걸음수"
                    )
                    RecordItem(
                        icon = painterResource(R.drawable.ic_time),
                        value = bestTime,
                        label = "운동 시간"
                    )
                    RecordItem(
                        icon = painterResource(R.drawable.ic_fire),
                        value = bestCalories,
                        label = "칼로리 소모량"
                    )
                }
            }
        }
}

@Composable
fun RecordItem(icon: Painter, value: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = icon,
            contentDescription = label,
            modifier = Modifier.size(24.dp)
        )
        Text(value)
        Text(label)
    }
}