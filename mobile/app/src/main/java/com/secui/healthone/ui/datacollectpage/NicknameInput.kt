package com.secui.healthone.ui.datacollectpage

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun NicknameInput() {
    val text = remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = text.value,
        onValueChange = { newText -> text.value = newText },
        label = { Text("닉네임을 입력하세요.") },
        modifier = Modifier.padding(8.dp)
    )
}