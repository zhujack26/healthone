package com.secui.healthone.ui.datacollectpage

import android.widget.NumberPicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.secui.healthone.R
import com.secui.healthone.constant.AppColors
import com.secui.healthone.viewmodel.UserViewModel

@Composable
fun Height(userViewModel: UserViewModel) {
    val (isDialogVisible, setDialogVisible) = remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "신장",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(52.dp))

        TextField(
            value = userViewModel.height.value, // 변경된 부분: cm 접미사를 제거
            onValueChange = {},
            visualTransformation = VisualTransformation.None,
            maxLines = 1,
            textStyle = TextStyle(
                color = colorResource(id = R.color.black),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            enabled = false,
            modifier = Modifier
                .width(240.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(32.dp))
                .clickable { setDialogVisible(true) },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = AppColors.green200,
                unfocusedIndicatorColor = AppColors.green200
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        )

        if (isDialogVisible) {
            Dialog(
                onDismissRequest = { setDialogVisible(false) },
            ) {
                Column(
                    modifier = Modifier.background(AppColors.white)
                ) {
                    AndroidView(
                        factory = { context ->
                            NumberPicker(context).apply {
                                minValue = 100
                                maxValue = 200
                                value = if (userViewModel.height.value.isEmpty()) 170 else userViewModel.height.value.toInt() // 변경된 부분: value의 타입을 Int로 변환
                                setOnValueChangedListener { _, _, newVal ->
                                    userViewModel.height.value = newVal.toString() // 변경된 부분: 새 값의 타입을 String으로 변환
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Button(
                        onClick = {
                            setDialogVisible(false)
                            if (userViewModel.height.value.isEmpty()) {
                                userViewModel.height.value = "170" // 초기 값의 타입을 String으로 변환
                            }
                        },
                        colors = ButtonDefaults
                            .outlinedButtonColors(
                                backgroundColor =
                                AppColors.green200
                            ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("확인",
                            fontSize = 12.sp,
                            color = AppColors.white)
                    }
                }
            }
        }
    }
}