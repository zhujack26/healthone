package com.secui.healthone.compose

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.material.Text
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.R
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.secui.healthone.ui.loginpage.*
//
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginPage(navController: NavController) {
    val context = LocalContext.current
    val gso = remember {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.server_client_id))
            .requestEmail()
            .build()
    }
    val googleSignInClient = remember { GoogleSignIn.getClient(context, gso) }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignInResult(navController, task)
        } else {
            Log.e("check", "Error1")
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .weight(0.70f)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.login_run),
                contentDescription = "Animation",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth,
            )
        }

        Text(
            text = "간편하게 가입하고 로그인해요",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        val googleSignInPainter: Painter = painterResource(R.drawable.login_google_logo)
        LoginButton(
            painter = googleSignInPainter,
            onClick = { signInWithGoogle(navController, launcher, googleSignInClient) }
        )

        Text(
            text = "로그인시 이용약관과 개인정보 보호호방침에 동의하게 됩니다.",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
private fun handleSignInResult(navController: NavController, task: Task<GoogleSignInAccount>) {
    try {
        val account = task.getResult(ApiException::class.java)
        val idToken = account.idToken
        Log.d("check", "ID Token: $idToken") // ID 토큰 값 확인
        sendIdTokenToServer(idToken)
        navController.navigate("datacollect1")
        Log.d("check", "check")
    } catch (e: Exception) {
        Log.e("check", "Error2", e)
    }
}

private fun signInWithGoogle(
    navController: NavController,
    launcher: androidx.activity.result.ActivityResultLauncher<Intent>,
    client: GoogleSignInClient
) {
    val signInIntent = client.signInIntent
    launcher.launch(signInIntent)
}

private fun sendIdTokenToServer(idToken: String?) {
    if (idToken == null) {
        Log.e("check", "ID token is null")
        return
    }

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val urlString = "http://healthonetest.com"
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            Log.d("check", "check2")
            connection.doOutput = true
            Log.d("check", "check3")
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            Log.d("check", "check4")
            val postData = "idToken=" + URLEncoder.encode(idToken, "UTF-8")

            DataOutputStream(connection.outputStream).use { outputStream ->
                outputStream.writeBytes(postData)
                outputStream.flush()
            }
            Log.d("check", "check5")
            val responseCode = connection.responseCode
            Log.d("check", "Response code from server: $responseCode")

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader(InputStreamReader(connection.inputStream)).use { reader ->
                    val response = reader.readText()
                    Log.i("check", "Signed in as: $response")
                }
            } else {
                Log.e("check", "Error sending ID token to backend. Response code: $responseCode")
            }

            connection.disconnect()
        } catch (e: Exception) {
            Log.e("check", "Error occurred in sendIdTokenToServer", e)
        }
    }
}