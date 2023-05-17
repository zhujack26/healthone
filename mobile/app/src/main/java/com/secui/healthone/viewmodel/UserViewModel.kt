package com.secui.healthone.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    var nickname = mutableStateOf("")
    val gender: MutableState<Boolean> = mutableStateOf(false)
}