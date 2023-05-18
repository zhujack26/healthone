package com.secui.healthone.constant

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.mutableStateOf

class HealthOnePage {
    companion object{
        val pageTitle = mutableStateOf<String>("");
        val accToken = mutableStateOf<String>("")
        val tempToken = mutableStateOf<String>("")
        val checkURL = mutableStateOf<String>("")
        val challengeURL = mutableStateOf<String>("")
    }
}