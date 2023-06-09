package com.secui.healthone.ui.datacollectpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.secui.healthone.constant.AppColors
import com.secui.healthone.viewmodel.UserViewModel

@Composable
fun ExcersiseAmount(userViewModel: UserViewModel) {
    var selectedAmount by remember { mutableStateOf<ExerciseAmount?>(null) }
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ExerciseButton(
                    amount = ExerciseAmount.LITTLE,
                    isSelected = selectedAmount == ExerciseAmount.LITTLE,
                    onSelected = {
                        selectedAmount = if (selectedAmount == it) null else it
                        userViewModel.workRate.value = it.name
                    }
                )
                Text(text = "적음", fontWeight = FontWeight.Bold)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ExerciseButton(
                    amount = ExerciseAmount.NORMAL,
                    isSelected = selectedAmount == ExerciseAmount.NORMAL,
                    onSelected = {
                        selectedAmount = if (selectedAmount == it) null else it
                        userViewModel.workRate.value = it.name
                    }
                )
                Text(text = "보통", fontWeight = FontWeight.Bold)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ExerciseButton(
                    amount = ExerciseAmount.MANY,
                    isSelected = selectedAmount == ExerciseAmount.MANY,
                    onSelected = {
                        selectedAmount = if (selectedAmount == it) null else it
                        userViewModel.workRate.value = it.name
                    }
                )
                Text(text = "많음", fontWeight = FontWeight.Bold)
            }
        }
    }
    if (selectedAmount == null) {
        Text(
            text = "선택해주세요",
            color = AppColors.mono400,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ExerciseButton(
    amount: ExerciseAmount,
    isSelected: Boolean,
    onSelected: (ExerciseAmount) -> Unit
) {
    val backgroundColor = if (isSelected) AppColors.blue600 else AppColors.mono200
    val icon = when (amount) {
        ExerciseAmount.LITTLE -> R.drawable.ic_excersise_less
        ExerciseAmount.NORMAL -> R.drawable.ic_excersise_normal
        ExerciseAmount.MANY -> R.drawable.ic_excersise_much
    }

    Box(
        modifier = Modifier
            .size(76.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable { onSelected(amount) },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = amount.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(56.dp)
        )
    }
}
  
enum class ExerciseAmount {
    LITTLE,
    NORMAL,
    MANY
}