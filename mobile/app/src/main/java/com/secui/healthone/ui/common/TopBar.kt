package com.secui.healthone.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.secui.healthone.MainPage
import kotlinx.coroutines.launch
import com.secui.healthone.R
import com.secui.healthone.compose.LoginPage
import com.secui.healthone.compose.MealPlanPage
import com.secui.healthone.compose.OverViewPage


@Preview
@Composable
fun TopBar() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Icon(painter = painterResource(id = R.drawable.ic_topbar_logo), contentDescription = "logo", tint = colorResource(id = R.color.black),
                    modifier = Modifier.size(60.dp)
                ) },
                backgroundColor = colorResource(id = R.color.white),
                actions = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(painter = painterResource(id = R.drawable.ic_topbar_toggle), contentDescription = "Menu", tint = colorResource(id = R.color.black),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        },
        drawerContent = {
            Column {
                DrawerButton(
                    text = "메인",
                    textColor = R.color.black,
                    onClick = {
                        navController.navigate("overviewpage")
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                )
                DrawerButton(
                    text = "심박수",
                    textColor = R.color.black,
                    onClick = {
//                        navController.navigate("")
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                )
                DrawerButton(
                    text = "식단",
                    textColor = R.color.black,
                    onClick = {
                      navController.navigate("mealPlanPage")
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                )
//                DrawerButton(
//                    text = "로그인(임시)",
//                    textColor = R.color.black,
//                    onClick = {
//                        navController.navigate("login")
//                        coroutineScope.launch {
//                            scaffoldState.drawerState.close()
//                        }
//                    }
//                )
            }
        },
        content = {
            NavHost(navController, startDestination = "overviewpage") {
                composable("overviewpage") {
                    OverViewPage()
                }
//                composable("") {
//                    ""() // 심박수 화면`
//                }
                composable("mealPlanPage") {
                    MealPlanPage() // 식단 화면
                }
//                composable("login") {
//                    LoginPage(navController = navController) // 로그인 화면
//                }
            }
        }
    )
}

@Composable
fun DrawerButton(text: String, textColor: Int = R.color.black, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text, fontSize = 16.sp, color = colorResource(id = textColor))
    }
}