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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.secui.healthone.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.secui.healthone.constant.AppColors

@Composable
fun GenderSelection(onGenderChange: (Boolean?) -> Unit) {
    var selectedGender by remember { mutableStateOf<Boolean?>(null) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "성별",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.width(60.dp))
        GenderButton(
            gender = Gender.Male,
            isSelected = selectedGender == true,
            onSelected = {
                selectedGender = if (selectedGender == true) null else true
                onGenderChange(selectedGender ?: false)
            }
        )
        Spacer(modifier = Modifier.width(16.dp))
        GenderButton(
            gender = Gender.Female,
            isSelected = selectedGender == false,
            onSelected = {
                selectedGender = if (selectedGender == false) null else false
                onGenderChange(selectedGender ?: false)
            }
        )
    }
}

@Composable
fun GenderButton(
    gender: Gender,
    isSelected: Boolean,
    onSelected: (Gender) -> Unit
) {
    val backgroundColor = if (isSelected) AppColors.blue800 else AppColors.mono200
    val icon = when (gender) {
        Gender.Male -> R.drawable.ic_male
        Gender.Female -> R.drawable.ic_female
    }

    Box(
        modifier = Modifier
            .size(84.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable { onSelected(gender) },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = gender.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(64.dp)
        )
    }
}

enum class Gender {
    Male,
    Female
}