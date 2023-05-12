package com.secui.healthone.ui.login

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun TermsDialog(onConfirm: () -> Unit) {
    AlertDialog(
        title = { Text("이용약관") },
        text = { Text("이용약관 내용") },
        onDismissRequest = { onConfirm() },
        buttons = {
            TextButton(onClick = onConfirm) {
                Text("확인")
            }
        }
    )
}