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
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task
import com.secui.healthone.ui.loginpage.*
import com.secui.healthone.util.PageRoutes
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
    //idToken
//    val gso = remember {
//        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(context.getString(R.string.server_client_id))
//            .requestEmail()
//            .build()
//    }

    //authCode
    val gso = remember {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestScopes(Scope(Scopes.EMAIL))
            .requestServerAuthCode(context.getString(R.string.server_client_id))
            .requestEmail()
            .build()
    }
    val googleSignInClient = remember { GoogleSignIn.getClient(context, gso) }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignInResult(navController, task)
            Log.d("check","check1, $result")
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
                painter = painterResource(R.drawable.onboarding_first),
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
//idToken
//private fun handleSignInResult(navController: NavController, task: Task<GoogleSignInAccount>) {
//    try {
//        val account = task.getResult(ApiException::class.java)
//        val idToken = account.idToken
//        Log.d("check", "ID Token: $idToken") // ID 토큰 값 확인
////        sendIdTokenToServer(idToken)
//        navController.navigate("datacollect1")
//        Log.d("check", "check")
//    } catch (e: Exception) {
//        Log.e("check", "Error2", e)
//    }
//}

//auth token
private fun handleSignInResult(navController: NavController, task: Task<GoogleSignInAccount>) {
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

private fun signInWithGoogle(
    navController: NavController,
    launcher: androidx.activity.result.ActivityResultLauncher<Intent>,
    client: GoogleSignInClient
) {
    val signInIntent = client.signInIntent
    launcher.launch(signInIntent)
}


private fun sendAuthCodeToServer(authCode: String?) {
    if (authCode == null) {
        Log.e("check", "authCode is null")
        return
    }
    else if (authCode != null) {
        Log.d("check", "authCode is not null")
    }
//private fun sendIdTokenToServer(idToken: String?) {
//    if (idToken == null) {
//        Log.e("check", "idToken is null")
//        return
//    }
//    else if (idToken != null) {
//        Log.d("check", "idToken is not null")
//    }
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val urlString = "http://192.168.31.33:8080/test"
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
                BufferedReader(InputStreamReader(connection.inputStream)).use { reader ->
                    val response = reader.readText()
                    Log.d("check", "Signed in as: $response")
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
