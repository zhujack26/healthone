package com.secui.healthone.ui.datacollectpage

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.view.ContextThemeWrapper
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.ui.common.AppColors
import com.secui.healthone.R

@Composable
fun BirthDate(
    value: String,
    onValueChange: (String) -> Unit = {},
    pattern: String = "yyyy년 MM월 dd일"
) {
    val context = LocalContext.current

    fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()

        val datePickerDialog = DatePickerDialog(
            ContextThemeWrapper(context, R.style.DatePickerDialogTheme),
            { _, year, month, dayOfMonth ->
                val newDate = Calendar.getInstance().apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, month)
                    set(Calendar.DAY_OF_MONTH, dayOfMonth)
                }
                onValueChange(
                    String.format("%1\$d년 %2\$d월 %3\$d일",
                        newDate.get(Calendar.YEAR),
                        newDate.get(Calendar.MONTH) + 1,
                        newDate.get(Calendar.DAY_OF_MONTH))
                )
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
    Row(verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "생년월일",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.width(20.dp))
        TextField(
            value = value,
            onValueChange = {},
            enabled = false,
            modifier = Modifier
                .clickable { showDatePickerDialog() }
                .width(240.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(32.dp)),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = AppColors.green200,
                unfocusedIndicatorColor = AppColors.green200
            ),
            textStyle = TextStyle(
                color = AppColors.black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),

        )
    }
}