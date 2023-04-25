package com.secui.healthone.ui.mealplanpage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TimeIntervalSelector() {
    val timeIntervals = listOf("일간", "주간")
    var expanded by remember { mutableStateOf(false) }
    var selectedInterval by remember { mutableStateOf(timeIntervals.first()) }

    Box {
        Text(
            text = selectedInterval,
            modifier = Modifier.clickable { expanded = true },
            style = TextStyle (fontSize = 16.sp, fontWeight = FontWeight.Bold)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            timeIntervals.forEach { interval ->
                DropdownMenuItem(
                    onClick = {
                        selectedInterval = interval
                        expanded = false
                    }
                ) {
                    Text(text = interval)
                }
            }
        }
    }
}
