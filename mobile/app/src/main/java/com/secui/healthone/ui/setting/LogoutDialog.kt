package com.secui.healthone.ui.setting

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun LogoutDialog(onConfirm: () -> Unit, onCancel: () -> Unit) {
    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("로그아웃") },
        text = { Text("로그아웃 하시겠습니까?") },
        confirmButton = {
            TextButton(onClick = onConfirm
            ) {
                Text("확인")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text("취소")
            }
        }
    )
}