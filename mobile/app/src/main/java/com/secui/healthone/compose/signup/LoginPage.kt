package com.secui.healthone.compose.signup

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.material.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.R
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.Scope
import com.secui.healthone.repository.GoogleSignInRepository
import com.secui.healthone.ui.login.PolicyDialog
import com.secui.healthone.ui.login.TermsDialog


@Composable
fun LoginPage(navController: NavController) {
    val termsOfServiceText = "이용약관"
    val privacyPolicyText = "개인정보 처리방침"
    val agreementText = "로그인시 ${termsOfServiceText}과 ${privacyPolicyText}에 동의하게 됩니다."
    val showTermsDialog = remember { mutableStateOf(false) }
    val showPolicyDialog = remember { mutableStateOf(false) }
    val annotatedText = remember {
        AnnotatedString.Builder(agreementText).apply {
            addStyle(SpanStyle(textDecoration = TextDecoration.Underline), agreementText.indexOf(termsOfServiceText), agreementText.indexOf(termsOfServiceText) + termsOfServiceText.length)
            addStringAnnotation("dialog", termsOfServiceText, agreementText.indexOf(termsOfServiceText), agreementText.indexOf(termsOfServiceText) + termsOfServiceText.length)

            addStyle(SpanStyle(textDecoration = TextDecoration.Underline), agreementText.indexOf(privacyPolicyText), agreementText.indexOf(privacyPolicyText) + privacyPolicyText.length)
            addStringAnnotation("dialog", privacyPolicyText, agreementText.indexOf(privacyPolicyText), agreementText.indexOf(privacyPolicyText) + privacyPolicyText.length)
        }
    }.toAnnotatedString()

    val context = LocalContext.current

    val gso = remember {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestScopes(Scope(Scopes.EMAIL))
            .requestServerAuthCode(context.getString(R.string.server_client_id))
            .requestEmail()
            .build()
    }

    val googleSignInClient = remember { GoogleSignIn.getClient(context, gso) }
    val repository = GoogleSignInRepository(context, gso, googleSignInClient)
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            repository.handleSignInResult(navController, task)
            Log.d("check", "check, $result")
        } else {
            Log.e("check", "Error result")
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
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
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "간편하게 가입하고 로그인하세요",
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Image(
            painter = painterResource(R.drawable.login_google_logo),
            contentDescription = "Login Button",
            modifier = Modifier
                .padding(16.dp)
                .size(48.dp)
                .clickable(onClick = {
                    repository.signInWithGoogle(
                        navController,
                        launcher,
                        googleSignInClient
                    )
                })
        )
        ClickableText(
            text = annotatedText,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.body2.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            onClick = { offset ->
                annotatedText.getStringAnnotations("dialog", offset, offset).firstOrNull()?.let { annotation ->
                    when (annotation.item) {
                        termsOfServiceText -> showTermsDialog.value = true
                        privacyPolicyText -> showPolicyDialog.value = true
                    }
                }
            }
        )
        if (showTermsDialog.value) {
            TermsDialog(
                onConfirm = { showTermsDialog.value = false },
            )
        }

        if (showPolicyDialog.value) {
            PolicyDialog(
                onConfirm = { showPolicyDialog.value = false },
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
//        Button(onClick = { repository.makeRequest(navController) }) {
//        }
    }
}

