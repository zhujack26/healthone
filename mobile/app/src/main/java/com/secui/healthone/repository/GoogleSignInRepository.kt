package com.secui.healthone.repository

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKeys
import com.google.gson.Gson
import com.secui.healthone.R
import com.secui.healthone.api.LoginApi
import com.secui.healthone.constant.HealthOnePage
import com.secui.healthone.constant.PageRoutes
import com.secui.healthone.instance.HeartRateInstance
import com.secui.healthone.util.PreferenceUtil
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GoogleSignInRepository (
    private val context: Context,
    private val gso: GoogleSignInOptions,
    private val googleSignInClient: GoogleSignInClient
) {
    private val cookieJar = object : CookieJar {
        private var cookies: List<Cookie>

        init {
            val actualCookies = ArrayList<Cookie>()
            cookies = actualCookies
        }

        override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
            this.cookies = cookies
        }

        override fun loadForRequest(url: HttpUrl): List<Cookie> {
            return cookies
        }

        // 추가된 메서드 ///////////////
        fun getCookiesByName(name: String): List<Cookie> {
            return cookies.filter { it.name == name }
        }
        ///////////////////////////
    }
    private val client = OkHttpClient.Builder()
        .cookieJar(cookieJar)
        .build()
//        .addInterceptor { chain ->
//            val request = chain.request()
//            val builder = request.newBuilder()
//            val cookies = cookieJar.loadForRequest(request.url)
//            for (cookie in cookies) {
//                builder.addHeader("Cookie", "${cookie.name}=${cookie.value}")
//            }
//            chain.proceed(builder.build())
//        }
//        .build()

    // Retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://back.apihealthone.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val loginApi = retrofit.create(LoginApi::class.java)

    private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    private val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
    private val sharedPreferences = EncryptedSharedPreferences.create(
        "secret_shared_prefs",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private var accessToken: String? = null
    fun handleSignInResult(navController: NavController, task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)
            val authCode = account.serverAuthCode
            Log.d("check", "Auth Code: $authCode")
            sendAuthCodeToServer(authCode, navController)
            navController.navigate(PageRoutes.DataCollectFirst.route) {
                popUpTo(PageRoutes.Login.route) { inclusive = true }
            }
            Log.d("check", "check")
        } catch (e: Exception) {
            Log.e("check", "Error2", e)
        }
    }

    fun signInWithGoogle(
        navController: NavController,
        launcher: androidx.activity.result.ActivityResultLauncher<Intent>,
        client: GoogleSignInClient
    ) {
        val signInIntent = client.signInIntent
        launcher.launch(signInIntent)
    }

    fun sendAuthCodeToServer(authCode: String?, navController: NavController) {
        if (authCode == null) {
            Log.e("check", "authCode is null")
            return
        } else if (authCode != null) {
            Log.d("check", "authCode is not null")
        }
        val requestBody = authCode.toRequestBody("text/plain".toMediaTypeOrNull())


        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = loginApi.sendAuthCodeToServer("http://login.apihealthone.com/auth/login", requestBody)
                if (response.isSuccessful) {
                    val accessTokenResponse = response.headers().get("Authorization")
                    Log.d("ResponseHeaders", "Headers: ${response.headers()}")
                    if (accessTokenResponse != null) {
                        sharedPreferences.edit().putString("access_token", accessTokenResponse).apply()
                        Log.d("check", "Received accessToken: $accessTokenResponse")
                        val url = response.raw().request.url
                        val cookies = cookieJar.loadForRequest(url)

                        ///////// 추가된 코드 /////////
                        val refreshToken = cookieJar.getCookiesByName("refreshtoken");
                        Log.d("check","리프래쉬 토큰 꺼내보기 : $refreshToken");


                        // accessToken을 가져오기
                        HealthOnePage.accToken.value =
                            sharedPreferences.getString("access_token", "") ?: ""
                        HealthOnePage.tempToken.value = context.getString(R.string.temp_token)
                        ////////////////////////

                        for (cookie in cookies) {
                            Log.d("check", "Received cookie: $cookie")
                        }
                        val cookiesJsonList = cookies.map { Gson().toJson(it) }
                        sharedPreferences.edit().putStringSet("cookies", cookiesJsonList.toSet()).apply()
                        val allEntries: Map<String, *> = sharedPreferences.getAll()
                        for ((key, value) in allEntries) {
                            Log.d("SharedPreferences", key + ": " + value.toString())
                        }
                    }
                } else {
                    Log.e("check", "Error. Response code : ${response.code()}")
                }

            } catch (e: Exception) {
                Log.e("check", "Error", e)
            }
        }
    }

//    fun makeRequest(navController: NavController, retryCount: Int = 0) {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                Log.d("check", "Cookies: ${getCookies()}")
//                Log.d("check", "AccessToken: ${getAccessToken()}")
//                val response = loginApi.verifyAuth("Bearer ${getAccessToken()}")
//                Log.d("check", "Response code : ${response.code()}")
//
//                when (response.code()) {
//                    200 -> {
//                        val responseBody = response.body()
//                        Log.d("check", "Response: $responseBody")
//                    }
//                    401 -> {
//                        Log.d("check", "Unauthorized")
//                        if (retryCount < 3) {
//                            delay(1000)
//                            makeRequest(navController, retryCount + 1)
//                            val newAccessToken = response.headers().get("Authorization")
//                            if (newAccessToken != null) {
//                                sharedPreferences.edit().putString("access_token", newAccessToken)
//                                    .apply()
//                                val url = response.raw().request.url
//                                val cookies = cookieJar.loadForRequest(url)
//                                val cookiesJsonList = cookies.map { Gson().toJson(it) }
//                                sharedPreferences.edit().putStringSet("cookies", cookiesJsonList.toSet()).apply()
//
//                                sharedPreferences.edit().putStringSet("cookies", cookiesJsonList.toSet()).apply()
//                                val allEntries: Map<String, *> = sharedPreferences.getAll()
//                                for ((key, value) in allEntries) {
//                                    Log.d("SharedPreferences", key + ": " + value.toString())
//                                }
//                            } else {
//                                Log.e("check", "new access token fail.")
//                            }
//                        }
//                    }
//                    403 -> {
//                        Log.d("check", "Forbidden, move to login page")
//                        navController.navigate(PageRoutes.DataCollectSecond.route)
//                    }
//                    else -> {
//                        Log.e("check", "Error. Response code : ${response.code()}")
//                    }
//                }
//            } catch (e: Exception) {
//                Log.e("check", "exception", e)
//            }
//        }
//    }


    private fun getAccessToken(): String {
        return sharedPreferences.getString("access_token", "") ?: ""
    }
    private fun getCookies(): List<Cookie> {
        val url = "https://back.apihealthone.com/".toHttpUrlOrNull()!!
        val cookiesJsonList = sharedPreferences.getStringSet("cookies", emptySet()) ?: emptySet()
        val cookies = cookiesJsonList.map { Gson().fromJson(it, Cookie::class.java) }
        return cookies
    }
}