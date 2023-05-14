package com.secui.healthone.ui.datacollectpage

import android.content.Context
import android.net.Uri

object ImageUri {
    fun saveImageUri(context: Context, uri: Uri) {
        val sharedPreferences = context.getSharedPreferences("com.secui.healthone", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("profileImageUri", uri.toString())
            apply()
        }
    }
    fun loadImageUri(context: Context): Uri? {
        val sharedPreferences = context.getSharedPreferences("com.secui.healthone", Context.MODE_PRIVATE)
        val uriString = sharedPreferences.getString("profileImageUri", null)
        return uriString?.let { Uri.parse(it) }
    }

    fun saveNicknameToPrefs(context: Context, nickname: String) {
        val sharedPref = context.getSharedPreferences("com.secui.healthone", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString("nickname", nickname)
            apply()
        }
    }
    fun getNicknameFromPrefs(context: Context): String {
        val sharedPref = context.getSharedPreferences("com.secui.healthone", Context.MODE_PRIVATE)
        return sharedPref.getString("nickname", "") ?: ""
    }
}