package com.secui.healthone.ui.sleep

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.secui.healthone.constant.AppColors
import com.secui.healthone.viewmodel.SleepViewModel

@Composable
fun SleepRecords(
    viewModel: SleepViewModel,
    index: Int
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    viewModel.errorMessage.observe(lifecycleOwner, Observer { errorMessage ->
        if (!errorMessage.isNullOrEmpty()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    })

    SleepRecordCard(sleepRecord = viewModel.sleepRecords[index], index = index, viewModel = viewModel)
}