package com.secui.healthone.ui.HealthStatus

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HealthInputItem(key: String, value: MutableState<Int?>, unit: String) {
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
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = unit, fontSize = 18.sp, fontWeight = FontWeight.Normal)
        }
    }
    Divider(color = Color.Gray, thickness = 1.dp)
}

@Composable
fun InputField(value: MutableState<Int?>) {
    Box(
        Modifier
            .width(40.dp)
    ) {
        BasicTextField(
            value = value.value?.toString() ?: "",
            onValueChange = { newValue ->
                // Try to convert the new value to an integer. If it's not possible, don't update the state.
                value.value = newValue.toIntOrNull()
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Normal),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Divider(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth(),
            color = Color.Gray,
            thickness = 1.dp
        )
    }
}
