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
import org.json.JSONObject
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class GoogleSignInRepository (
    private val context: Context,
    private val gso: GoogleSignInOptions,
    private val googleSignInClient: GoogleSignInClient
) {
    private var accessToken: String? = null
    fun handleSignInResult(navController: NavController, task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)
            val authCode = account.serverAuthCode
            Log.d("check", "Auth Code: $authCode") //authCode 값 확인
            sendAuthCodeToServer(authCode)
            navController.navigate(PageRoutes.DataCollectFirst.route)
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

    fun sendAuthCodeToServer(authCode: String?) {
        if (authCode == null) {
            Log.e("check", "authCode is null")
            return
        } else if (authCode != null) {
            Log.d("check", "authCode is not null")
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val urlString = "http://192.168.31.33:8080/test"
//                val urlString = "https://back.apihealthone.com/auth/login"
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
                    Log.d("check", "check, $postData")
                    outputStream.flush()
                }
                val responseCode = connection.responseCode
                Log.d("check", "Response code : $responseCode")

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val accessTokenResponse = connection.getHeaderField("accessToken")
                    Log.d("check", "Received accessToken: $accessTokenResponse")

                    BufferedReader(InputStreamReader(connection.inputStream)).use { reader ->
                        //서버로부터 받은 응답을 읽기 위해 BufferedReader와 InputStreamReader를 사용
                        val response = reader.readText()
                        val jsonResponse = JSONObject(response)
                        accessToken = jsonResponse.getString("accessToken")
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
}