package com.secui.healthone.compose

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

@Composable
fun LoginPage(navController: NavController) {
    val context = LocalContext.current
    val gso = remember {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
    }
    val googleSignInClient = remember { GoogleSignIn.getClient(context, gso) }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        handleSignInResult(task)
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
//            onClick = { signInWithGoogle(launcher, googleSignInClient) }
//            나중에 구글로그인 구현할 때 사용
            onClick = { navController.navigate("dataCollectPage") }
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
private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
    try {
        val account = task.getResult(ApiException::class.java)
    } catch (e: ApiException) {
    }
}

private fun signInWithGoogle(
    launcher: androidx.activity.result.ActivityResultLauncher<Intent>,
    client: GoogleSignInClient
) {
    val signInIntent = client.signInIntent
    launcher.launch(signInIntent)
}