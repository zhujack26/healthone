package com.secui.healthone.ui.datacollectpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.secui.healthone.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ExcersiseAmount() {
    var selectedAmount by remember { mutableStateOf<ExerciseAmount?>(null) }
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            ExerciseButton(
                amount = ExerciseAmount.Less,
                isSelected = selectedAmount == ExerciseAmount.Less,
                onSelected = {
                    selectedAmount = if (selectedAmount == it) null else it
                }
            )
            ExerciseButton(
                amount = ExerciseAmount.Normal,
                isSelected = selectedAmount == ExerciseAmount.Normal,
                onSelected = {
                    selectedAmount = if (selectedAmount == it) null else it
                }
            )
            ExerciseButton(
                amount = ExerciseAmount.Much,
                isSelected = selectedAmount == ExerciseAmount.Much,
                onSelected = {
                    selectedAmount = if (selectedAmount == it) null else it
                }
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "적음", fontWeight = FontWeight.Bold)
            Text(text = "보통", fontWeight = FontWeight.Bold)
            Text(text = "많음", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ExerciseButton(
    amount: ExerciseAmount,
    isSelected: Boolean,
    onSelected: (ExerciseAmount) -> Unit
) {
    val backgroundColor = if (isSelected) colorResource(id = R.color.blue800) else colorResource(id = R.color.mono200)
    val icon = when (amount) {
        ExerciseAmount.Less -> R.drawable.ic_excersise_less
        ExerciseAmount.Normal -> R.drawable.ic_excersise_normal
        ExerciseAmount.Much -> R.drawable.ic_excersise_much
    }

    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable { onSelected(amount) },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = amount.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(80.dp)
        )
    }
}

enum class ExerciseAmount {
    Less,
    Normal,
    Much
}