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
import com.secui.healthone.util.PageRoutes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.secui.healthone.api.LoginApi
import kotlinx.coroutines.delay
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieHandler
import java.net.CookieManager

class GoogleSignInRepository (
    private val context: Context,
    private val gso: GoogleSignInOptions,
    private val googleSignInClient: GoogleSignInClient
) {
    // Retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.31.33/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // API
    private val loginApi = retrofit.create(LoginApi::class.java)

    private val cookieJar = object : CookieJar {
        private val cookieStore = HashMap<HttpUrl, List<Cookie>>()

        override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
            cookieStore[url] = cookies
        }

        override fun loadForRequest(url: HttpUrl): List<Cookie> {
            return cookieStore[url] ?: ArrayList()
        }
    }
    private val client = OkHttpClient.Builder()
        .cookieJar(cookieJar)
        .build()

    private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    private val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
    private val sharedPreferences = EncryptedSharedPreferences.create(
        "secret_shared_prefs",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
    init {
        val cookieManager = CookieManager()
        CookieHandler.setDefault(cookieManager)
    }

    private var accessToken: String? = null
    fun handleSignInResult(navController: NavController, task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)
            val authCode = account.serverAuthCode
            Log.d("check", "Auth Code: $authCode")
            sendAuthCodeToServer(authCode, navController)
//            navController.navigate(PageRoutes.DataCollectFirst.route)
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

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = loginApi.sendAuthCodeToServer(authCode)

                if (response.isSuccessful) {
                    val accessTokenResponse = response.headers().get("Authorization")
                    if (accessTokenResponse != null) {
                        sharedPreferences.edit().putString("access_token", accessTokenResponse).apply()
                        Log.d("check", "Received accessToken: $accessTokenResponse")
                    }
                } else {
                    Log.e("check", "Error. Response code : ${response.code()}")
                }

            } catch (e: Exception) {
                Log.e("check", "Error", e)
            }
        }
    }

    fun makeRequest(navController: NavController, retryCount: Int = 0) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = loginApi.verifyAuth("Bearer ${getAccessToken()}")

                Log.d("check", "Response code : ${response.code()}")

                when (response.code()) {
                    200 -> {
                        val responseBody = response.body()
                        Log.d("check", "Response: $responseBody")
                    }
                    401 -> {
                        Log.d("check", "Unauthorized")
                        if (retryCount < 3) {
                            delay(1000)
                            makeRequest(navController, retryCount + 1)
                            val newAccessToken = response.headers().get("Authorization")
                            if (newAccessToken != null) {
                                sharedPreferences.edit().putString("access_token", newAccessToken)
                                    .apply()
                            } else {
                                Log.e("check", "new access token fail.")
                            }
                        }
                    }
                    403 -> {
                        Log.d("check", "Forbidden, move to login page")
                        navController.navigate(PageRoutes.DataCollectSecond.route)
                    }
                    else -> {
                        Log.e("check", "Error. Response code : ${response.code()}")
                    }
                }
            } catch (e: Exception) {
                Log.e("check", "exception", e)
            }
        }
    }


    private fun getAccessToken(): String {
        return sharedPreferences.getString("access_token", "") ?: ""
    }
}