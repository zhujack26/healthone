package com.secui.healthone.instance

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.mutableStateOf
import com.secui.healthone.SplashActivity
import com.secui.healthone.repository.GoogleSignInRepository
import com.secui.healthone.service.HeartRateService
import com.secui.healthone.util.PreferenceUtil
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.CookieManager
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
        }

        var builder = OkHttpClient().newBuilder()
        var okHttpClient = builder
            //.cookieJar(cookieJar)
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100,TimeUnit.SECONDS)
            .writeTimeout(100,TimeUnit.SECONDS)
            .addInterceptor(myInterceptor())
            .build()

        class myInterceptor : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain) : Response = with(chain) {
                Log.d(LOG, "리프레쉬 토큰 꺼낸 결과 : ${refreshToken.value}")
                Log.d(LOG, "엑세스 토큰 꺼낸 결과 : ${accToken.value}")
                val newRequest = request().newBuilder()
                    .addHeader("Authorization", "Bearer ${accToken.value}")
                    .addHeader("Cookie", "refreshtoken=${refreshToken.value}")
                    .build()
                Log.d(LOG, "리퀘스트 정보 : ${newRequest.toString()}")
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
        // https://back.apihealthone.com/check/
        // http://check.apihealthone.com/
        const val BACK_URL = "https://back.apihealthone.com/";
        const val URL = "http://check.apihealthone.com/"
        // val prefs = PreferenceUtil(SplashActivity.context as Context);
        val accToken = mutableStateOf<String>("");
        val refreshToken = mutableStateOf<String>("")
        val tempAccToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJlbWFpbCIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwibm8iOjF9.Qe2hD2Qjh078O1Nt6H9Ti_zZtC70BlmULh6O3ckuSOQ"

        const val LOG = "HEART_RATE_INSTANCE";


    }


}

