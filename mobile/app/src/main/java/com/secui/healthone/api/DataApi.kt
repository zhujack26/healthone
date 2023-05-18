package com.secui.healthone.api

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import java.io.File
import java.io.FileOutputStream

interface DataApi {
    @GET("info/check-data-download/sleep")
    suspend fun downloadHealthRecordData(@Header("Authorization") accessToken: String): ResponseBody

    @GET("info/check-data-download/healthinfo")
    suspend fun downloadHealthData(@Header("Authorization") accessToken: String): ResponseBody

    @GET("check/check-data-download/walk")
    suspend fun downloadWalkData(@Header("Authorization") accessToken: String): ResponseBody

    @GET("check/check-data-download/sleep")
    suspend fun downloadSleepData(@Header("Authorization") accessToken: String): ResponseBody

    @GET("check/check-data-download/heart-rate")
    suspend fun downloadHeartRateData(@Header("Authorization") accessToken: String): ResponseBody
}

// Retrofit 클라이언트 생성
fun createRetrofitClient(baseUrl: String): Retrofit {
    val loggingInterceptor = HttpLoggingInterceptor { message ->
        Log.d("UserInformDownPage", "Request Body: $message")
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
}

// 다운로드 함수
suspend fun downloadData(context: Context, url: String, accessToken: String) {
    Log.d("DownloadData", "Start downloading data...")
    val retrofit = createRetrofitClient("https://back.apihealthone.com/")
    val service = retrofit.create(DataApi::class.java)

    val responseBody = when (url) {
        "https://back.apihealthone.com/info/check-data-download/sleep" -> service.downloadHealthRecordData("Bearer $accessToken")
        "https://back.apihealthone.com/info/check-data-download/healthinfo" -> service.downloadHealthData("Bearer $accessToken")
        "https://back.apihealthone.com/check/check-data-download/walk" -> service.downloadWalkData("Bearer $accessToken")
        "https://back.apihealthone.com/check/check-data-download/sleep" -> service.downloadSleepData("Bearer $accessToken")
        "https://back.apihealthone.com/check/check-data-download/heart-rate" -> service.downloadHeartRateData("Bearer $accessToken")
        else -> throw IllegalArgumentException("Invalid URL")
    }
    Log.d("DownloadData", "Data downloaded successfully...")
    // 파일 저장
    val file = File(context.filesDir, "downloaded_data.txt")
    Log.d("DownloadData", "File saved0")

    val outputStream = FileOutputStream(file)
    Log.d("DownloadData", "File saved1")
    outputStream.write(responseBody.bytes())
    Log.d("DownloadData", "File saved2")
    outputStream.close()
    Log.d("DownloadData", "File saved3")
    // 다운로드 완료 후 파일 열기
    val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
    Log.d("DownloadData", "File open1")
    val intent = Intent(Intent.ACTION_VIEW)
    Log.d("DownloadData", "File open2")
    intent.setDataAndType(uri, "text/plain")
    intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
    Log.d("DownloadData", "File open3")
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    Log.d("DownloadData", "File open4")
    context.startActivity(intent)
}