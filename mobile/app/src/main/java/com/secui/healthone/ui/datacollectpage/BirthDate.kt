package com.secui.healthone.ui.datacollectpage

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun BirthDate(
    value: String,
    onValueChange: (String) -> Unit = {},
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    pattern: String = "yyyy년 MM월 dd일"
) {
    val context = LocalContext.current

    fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()

        val datePickerDialog = DatePickerDialog(context,
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
    Text(text = "생년월일")
    TextField(
        value = value,
        onValueChange = { /* do nothing */ },
        enabled = false,
        modifier = Modifier.clickable { showDatePickerDialog() },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        label = { /*Empty Composable*/ }
    )
}