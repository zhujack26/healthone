package com.secui.healthone.util

import androidx.appcompat.app.AppCompatActivity
import com.secui.healthone.service.HealthOneNetworkService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.CookieManager
import java.util.concurrent.TimeUnit

class HealthOneRetrofitInstance : AppCompatActivity() {

    object RetrofitInstance {
//        private val retrofit by lazy {
//            Retrofit.Builder()
//                .baseUrl("http://192.168.31.33:8080")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        }
//        val api : HealthOneNetworkService by lazy {
//            retrofit.create(HealthOneNetworkService::class.java)
//        }

        var builder = OkHttpClient().newBuilder()
        var okHttpClient = builder
            //.cookieJar(JavaNetCookieJar(CookieManager()))
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100,TimeUnit.SECONDS)
            .writeTimeout(100,TimeUnit.SECONDS)
            .addInterceptor(myInterceptor())
            .build()

        // Base64 인코더를 사용하여 문자열 인코딩
//        val encodedString:String = Base64.getEncoder().encodeToString(keyString.toByteArray())


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
                .baseUrl(SERVER_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val api : HealthOneNetworkService by lazy {
            retrofit.create(HealthOneNetworkService::class.java)
        }
    }

    companion object {
        const val hyoURL = "http://192.168.31.33:8080/"
        const val SERVER_URL = "http://a80d3a967a5514702bfe8ba3e8b52871-1335940738.ap-northeast-2.elb.amazonaws.com:8080/";
    }


}

