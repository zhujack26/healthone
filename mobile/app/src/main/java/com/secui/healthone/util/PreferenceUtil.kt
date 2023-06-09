package com.secui.healthone.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {
    private lateinit var context: Context

    fun init(context: Context) {
        this.context = context.applicationContext
    }

    fun getString(resId: Int): String {
        return context.getString(resId)
    }

    private val prefs: SharedPreferences =
        context.getSharedPreferences("healthone_prefs", Context.MODE_PRIVATE)

    private val tokenPrefs: SharedPreferences =
        context.getSharedPreferences("secret_shared_prefs", Context.MODE_PRIVATE);

    fun getString(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }

    fun getTokenString(key:String, defValue:String):String{
        return tokenPrefs.getString(key, defValue).toString()
    }

    fun setTokenString(key:String, str:String) {
        return tokenPrefs.edit().putString(key, str).apply()
    }

}