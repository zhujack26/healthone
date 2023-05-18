package com.secui.healthone.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    var nickname = mutableStateOf("")
    val gender: MutableState<Boolean> = mutableStateOf(false)
    val birthdate = mutableStateOf("")
    val height = mutableStateOf("")
    val weight = mutableStateOf("")
    val stepGoal = mutableStateOf(6000)
    val sleepTime = mutableStateOf("")
    val wakeUpTime = mutableStateOf("")
    val workRate = mutableStateOf("")
}