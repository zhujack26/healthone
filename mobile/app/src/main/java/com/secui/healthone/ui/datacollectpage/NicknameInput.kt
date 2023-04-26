package com.secui.healthone.ui.datacollectpage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.R

@Composable
fun NicknameInput() {
    val (textState, setTextState) = remember {
        mutableStateOf("")
    }
    Row(verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "닉네임",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.width(32.dp))
        TextField(
            value = textState,
            onValueChange = setTextState,
            visualTransformation = VisualTransformation.None,
            maxLines = 1,
            textStyle = TextStyle(
                color = colorResource(id = R.color.black),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .width(240.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(32.dp)),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )
    }
}