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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
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
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker
import kotlin.math.absoluteValue

@Composable
fun LoginPage(navController: NavController) {
    val context = LocalContext.current

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
            Log.d("check","check, $result")
        } else {
            Log.e("check", "Error1")
        }
    }
    var onBoardingState = remember { mutableStateOf(0) }
    val onBoardingImages = listOf(
        R.drawable.onboarding_third,
        R.drawable.onboarding_second,
        R.drawable.onboarding_first,

    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        val offsetX = remember { mutableStateOf(0f) }
        val velocityTracker = remember { VelocityTracker() }
        val scrollableState = rememberScrollableState { delta ->
            if (delta.absoluteValue >= 200f) {
                if (delta > 0) {
                    onBoardingState.value = (onBoardingState.value + 1) % onBoardingImages.size
                } else {
                    onBoardingState.value = (onBoardingState.value - 1 + onBoardingImages.size) % onBoardingImages.size
                }
            }
            offsetX.value = 0f
            delta
        }
        Box(
            modifier = Modifier
                .weight(0.70f)
                .fillMaxWidth()
                .scrollable(
                    orientation = Orientation.Horizontal,
                    enabled = true,
                    state = scrollableState,
                )
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { change, dragAmount ->
                            velocityTracker.addPosition(change.uptimeMillis, Offset(dragAmount, 0f))
                            offsetX.value += dragAmount
                        },
                        onDragEnd = {
                            val velocity = velocityTracker.calculateVelocity().x
                            if (velocity.absoluteValue >= 200f) {
                                if (velocity > 0) {
                                    onBoardingState.value = (onBoardingState.value + 1) % onBoardingImages.size
                                } else {
                                    onBoardingState.value = (onBoardingState.value - 1 + onBoardingImages.size) % onBoardingImages.size
                                }
                            }
                            offsetX.value = 0f
                        }
                    )
                }
                .graphicsLayer {
                    translationX = offsetX.value
                }
        ) {
            Row {
                onBoardingImages.forEachIndexed { index, drawable ->
                    Image(
                        painter = painterResource(drawable),
                        contentDescription = "Animation",
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer {
                                translationX = offsetX.value * (index - onBoardingState.value)
                            },
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp)
        ) {
            onBoardingImages.forEachIndexed { index, _ ->
                CircleIndicator(
                    isSelected = index == onBoardingState.value % onBoardingImages.size,
                    modifier = Modifier.padding(4.dp)
                )
            }
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
@Composable
fun CircleIndicator(isSelected: Boolean, modifier: Modifier = Modifier) {
    val color = if (isSelected) colorResource(R.color.black) else colorResource(R.color.mono200)
    Box(
        modifier = modifier
            .size(8.dp)
            .clip(CircleShape)
            .background(color)
    )
}
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
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val urlString = "http://192.168.31.33/test"
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.doOutput = true
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            val postData = URLEncoder.encode(authCode, "UTF-8")
            DataOutputStream(connection.outputStream).use { outputStream ->
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
