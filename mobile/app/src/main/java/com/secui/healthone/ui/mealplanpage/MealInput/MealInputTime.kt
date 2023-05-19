package com.secui.healthone.ui.mealplanpage.MealInput

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import com.secui.healthone.R

@Composable
fun MealInputTime(onIntervalSelected: (String) -> Unit) {
    val timeIntervals = listOf("아침", "점심", "저녁", "간식")
    var expanded by remember { mutableStateOf(false) }
    var selectedInterval by remember { mutableStateOf(timeIntervals.first()) }

    Box {
        Row(
            modifier = Modifier.clickable { expanded = !expanded },
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 음식 이미지 추가
            Image(
                painter = painterResource(id = R.drawable.ic_food),
                contentDescription = "음식 이미지",
                modifier = Modifier.width(24.dp).height(24.dp)
            )

            // 간격 조정
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = selectedInterval,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )

            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Arrow Dropdown",
                modifier = Modifier.rotate(if (expanded) 270f else 0f)
            )
        }

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
                        onIntervalSelected(interval)
                    }
                ) {
                    Text(text = interval)
                }
            }
        }
    }
}