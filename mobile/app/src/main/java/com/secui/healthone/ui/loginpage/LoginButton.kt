package com.secui.healthone.ui.loginpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun LoginButton(painter: Painter, onClick: () -> Unit) {
    Image(
        painter = painter,
        contentDescription = "Login Button",
        modifier = Modifier
            .padding(16.dp)
            .size(48.dp)
            .clickable(onClick = onClick)
    )
}