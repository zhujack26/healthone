package com.secui.healthone.instance

import android.util.Log
import com.secui.healthone.constant.HealthOnePage
import com.secui.healthone.service.ChallengeService
import com.secui.healthone.service.HeartRateService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class ChallengeInstance {
    object RetrofitInstance {
        var builder = OkHttpClient().newBuilder()
        var okHttpClient = builder
            //.cookieJar(JavaNetCookieJar(CookieManager()))
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(myInterceptor())
            .build()

        class myInterceptor : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain) : Response = with(chain) {
                val newRequest = request().newBuilder()
                    .addHeader("Authorization", "Bearer ${HealthOnePage.accToken.value}")
                    .build()
                proceed(newRequest)
            }
        }

        private val retrofit by lazy {
            Log.d("CHALLENEGE_INSTNACE", "${okHttpClient.toString()}")
            Retrofit.Builder()
                .baseUrl("https://back.apihealthone.com/challenge/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val api : ChallengeService by lazy {
            retrofit.create(ChallengeService::class.java)
        }
    }

    companion object {

    }
}