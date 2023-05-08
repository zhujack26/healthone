package com.secui.healthone.ui.HealthStatus

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HealthInputItem(key: String, value: MutableState<String>, unit: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = key,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Row {
            InputField(value = value)
            Text(text = unit, fontSize = 18.sp, fontWeight = FontWeight.Normal)
        }
    }
    Divider(color = Color.Gray, thickness = 1.dp)
}

@Composable
fun InputField(value: MutableState<String>) {
    BasicTextField(
        value = value.value,
        onValueChange = { newValue -> value.value = newValue },
        modifier = Modifier
            .width(60.dp)
            .border(1.dp, Color.Gray),
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Normal),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}
