package com.secui.healthone.instance

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun DataDownInstance(baseUrl: String): Retrofit {
    val client = OkHttpClient.Builder().build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
}