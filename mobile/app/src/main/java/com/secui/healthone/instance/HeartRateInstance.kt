package com.secui.healthone.instance

import androidx.appcompat.app.AppCompatActivity
import com.secui.healthone.service.HeartRateService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class HeartRateInstance : AppCompatActivity() {

    object RetrofitInstance {

        var builder = OkHttpClient().newBuilder()
        var okHttpClient = builder
            //.cookieJar(JavaNetCookieJar(CookieManager()))
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100,TimeUnit.SECONDS)
            .writeTimeout(100,TimeUnit.SECONDS)
            .addInterceptor(myInterceptor())
            .build()

        class myInterceptor : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain) : Response = with(chain) {
                val newRequest = request().newBuilder()
//                    .addHeader("Authorization", "Basic ${encodedString.toString()}")
                    .build()
                proceed(newRequest)
            }
        }

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val api : HeartRateService by lazy {
            retrofit.create(HeartRateService::class.java)
        }
    }

    companion object {
        // http://check.apihealthone.com/
        const val URL = "https://back.apihealthone.com/check/"

    }


}
