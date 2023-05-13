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
}