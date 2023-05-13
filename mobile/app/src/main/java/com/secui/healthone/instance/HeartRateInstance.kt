package com.secui.healthone.instance

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.mutableStateOf
import com.secui.healthone.SplashActivity
import com.secui.healthone.service.HeartRateService
import com.secui.healthone.util.PreferenceUtil
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class HeartRateInstance : AppCompatActivity() {

    object RetrofitInstance {

        val cookieJar = object : CookieJar {
            private val cookieStore = HashMap<String, MutableList<Cookie>>()

            override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                cookieStore[url.host] = cookies.toMutableList()
            }

            override fun loadForRequest(url: HttpUrl): List<Cookie> {
                val cookies = cookieStore[url.host] ?: return emptyList()
                val refreshTokenValue = refreshToken.value// 여기서 refresh token을 가져오세요
                Log.d(LOG, "토큰을 적재합니다 (loadForRequest) : $refreshTokenValue")

                // Add your Refresh Token to the cookies as a new Cookie object
                val refreshTokenCookie = Cookie.Builder()
                    .name("refreshtoken")
                    .value(refreshTokenValue)
                    .domain(url.host)
                    .build()

                val newCookies = ArrayList(cookies)
                newCookies.add(refreshTokenCookie)

                cookieStore[url.host] = newCookies

                return newCookies
            }


//            override fun loadForRequest(url: HttpUrl): List<Cookie> {
//
//                val cookies = cookieStore[url.host] ?: return emptyList()
//                // Add your Refresh Token to the cookies as a new Cookie object
//                val refreshTokenCookie = Cookie.Builder()
//                    .name("refreshtoken")
//                    .value(refreshToken.value)
//                    .domain(url.host)
//                    .build()
//
//                return cookies + refreshTokenCookie
//            }
        }

        var builder = OkHttpClient().newBuilder()
        var okHttpClient = builder
            .cookieJar(cookieJar)
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100,TimeUnit.SECONDS)
            .writeTimeout(100,TimeUnit.SECONDS)
            .addInterceptor(myInterceptor())
            .build()

        class myInterceptor : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain) : Response = with(chain) {
                Log.d(LOG, "엑세스 토큰 꺼낸 결과 : ${accToken.value}")
                val newRequest = request().newBuilder()
                    .addHeader("Authorization", "Bearer ${accToken.value}")
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
        // val prefs = PreferenceUtil(SplashActivity.context as Context);
        val accToken = mutableStateOf<String>("");
        val refreshToken = mutableStateOf<String>("")

        const val LOG = "HEART_RATE_INSTANCE";
    }


}

