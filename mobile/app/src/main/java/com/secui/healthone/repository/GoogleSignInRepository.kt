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
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import kotlinx.coroutines.delay
import java.net.CookieHandler
import java.net.CookieManager
import java.net.HttpCookie
import java.net.URI

class GoogleSignInRepository (
    private val context: Context,
    private val gso: GoogleSignInOptions,
    private val googleSignInClient: GoogleSignInClient
) {

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
                val urlString = "http://192.168.31.33/auth/login"
                val url = URL(urlString)
                val connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = "POST"
                Log.d("check", "check2")
                connection.doOutput = true
                Log.d("check", "check3")
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
                Log.d("check", "check4")

                val postData = URLEncoder.encode(authCode, "UTF-8")
                DataOutputStream(connection.outputStream).use { outputStream ->
                    Log.d("check", "check5")
                    outputStream.writeBytes(authCode)
                    Log.d("check", "check6, $postData")
                    outputStream.flush()
                }
                val responseCode = connection.responseCode
                Log.d("check", "Response code : $responseCode")

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val setCookieHeaders = connection.getHeaderFields()["Set-Cookie"]
                    if (setCookieHeaders != null) {
                        val cookieManager = CookieHandler.getDefault() as CookieManager
                        for (cookieStr in setCookieHeaders) {
                            val httpCookie = HttpCookie.parse(cookieStr)[0]
                            val cookieDomain = URI.create(urlString)
                            cookieManager.getCookieStore().add(cookieDomain, httpCookie)
                        }
                    }
                    val accessTokenResponse = connection.getHeaderField("Authorization")
                    Log.d("check", "Received accessToken: $accessTokenResponse")
                    sharedPreferences.edit().putString("access_token", accessTokenResponse).apply()

                    BufferedReader(InputStreamReader(connection.inputStream)).use { reader ->
                        val response = reader.readText()
//                        val jsonResponse = JSONObject(response)
//                        accessToken = jsonResponse.getString("accessToken")
                        Log.d("check", "Signed in as: $response")
//                        Log.d("check", "Signed in as: $accessToken")
                    }
                } else {
                    Log.e("check", "Error. Response code : $responseCode")
                }

                connection.disconnect()
            } catch (e: Exception) {
                Log.e("check", "exception", e)
            }
        }
    }
    fun makeRequest(navController: NavController) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val urlString = "http://192.168.31.33/auth/verify"
                val url = URL(urlString)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                connection.setRequestProperty("Authorization", "Bearer ${getAccessToken()}")
                Log.d("check", "Authorization: Bearer ${getAccessToken()}")
                connection.setRequestProperty("Cookie", getCookies(urlString))
                Log.d("check", "Cookie: Bearer ${getCookies(urlString)}")
                val responseCode = connection.responseCode
                Log.d("check", "Response code : $responseCode")

                when (responseCode) {
                    HttpURLConnection.HTTP_OK -> {
                        val setCookieHeaders = connection.getHeaderFields()["Set-Cookie"]
                        if (setCookieHeaders != null) {
                            for (cookie in setCookieHeaders) {
                                Log.d("check", "Set-Cookie: $cookie")
                            }
                        }
                        BufferedReader(InputStreamReader(connection.inputStream)).use { reader ->
                            val response = reader.readText()
                            Log.d("check", "Response: $response")
                        }
                    }
                    HttpURLConnection.HTTP_UNAUTHORIZED -> {
                        Log.d("check", "Unauthorized")
                        delay(1000)
                        makeRequest(navController)
                        val newAccessToken = connection.getHeaderField("Authorization")
                        if (newAccessToken != null) {
                            sharedPreferences.edit().putString("access_token", newAccessToken).apply()

                        } else {
                            Log.e("check", "new access token fail.")
                        }
                    }
                    HttpURLConnection.HTTP_FORBIDDEN -> {
                        Log.d("check", "Forbidden, move to login page")
                        navController.navigate(PageRoutes.DataCollectSecond.route)
                    }
                    else -> {
                        Log.e("check", "Error. Response code : $responseCode")
                    }
                }

                connection.disconnect()
            } catch (e: Exception) {
                Log.e("check", "exception", e)
            }
        }
    }

    private fun getAccessToken(): String {
        return sharedPreferences.getString("access_token", "") ?: ""
    }
    private fun getCookies(urlString: String): String {
        val cookieManager = CookieHandler.getDefault() as CookieManager
        val cookies = cookieManager.getCookieStore().get(URI.create(urlString))
        return cookies.joinToString("; ") { "${it.name}=${it.value}" }
    }
}