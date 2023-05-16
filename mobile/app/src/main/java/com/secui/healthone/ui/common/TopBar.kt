package com.secui.healthone.ui.common

import android.content.Context
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.secui.healthone.R
import com.secui.healthone.constant.AppColors
import com.secui.healthone.constant.HealthOnePage
import com.secui.healthone.constant.PageRoutes
import com.secui.healthone.ui.datacollectpage.ImageUri

@Composable
fun TopBar(context: Context) {
    val navController = rememberNavController()
    val menuOpen = remember { mutableStateOf(false) } // 메뉴 상태를 기억하는 변수
    val menuOffset = animateDpAsState(if (menuOpen.value) 0.dp else 272.dp) // 애니메이션 상태
    val currentTitle = remember { mutableStateOf("메인") } // 현재 타이틀을 저장하는 변수

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_topbar_logo),
                                contentDescription = "logo",
                                modifier = Modifier
                                    .size(60.dp)
                                    .clickable { navController.navigate(PageRoutes.OverView.route) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            inclusive = true
                                        }
                                        launchSingleTop = true
                                    }}
                            )
                            Spacer(modifier = Modifier.width(76.dp))
                            Text(
                                text = HealthOnePage.pageTitle.value,
                                color = AppColors.mono900,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    },
                    backgroundColor = AppColors.white,
                    actions = {
                        IconButton(onClick = {
                            navController.navigate(PageRoutes.Alert.route)
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_bell),
                                contentDescription = "Alert",
                                modifier = Modifier.size(24.dp),
                                tint = AppColors.mono900
                            )
                        }
                        IconButton(onClick = {
                            menuOpen.value = !menuOpen.value
                        }) { // 상태를 업데이트하여 메뉴를 열고 닫습니다.
                            Icon(
                                painter = painterResource(id = R.drawable.ic_topbar_toggle),
                                contentDescription = "Menu",
                                modifier = Modifier.size(24.dp),
                                tint = AppColors.mono900
                            )
                        }
                    }
                )
            }
            TopBarNavigation(navController, context)
        }
        if (menuOpen.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { menuOpen.value = false }
                    )
            )
        }
            Column(
                modifier = Modifier
                    .offset(x = menuOffset.value) // 애니메이션 값을 적용
                    .width(272.dp)
                    .fillMaxHeight()
                    .background(AppColors.white)
                    .border(2.dp, AppColors.mono200) // 왼쪽 경계선 추가
                    .align(Alignment.TopEnd)
            ) {
                DrawerButton(
                    text = ImageUri.getNicknameFromPrefs(context),
                    showImage = true,
                    onClick = {
                        navController.navigate(PageRoutes.My.route)
                    }
                )
                Divider(color = AppColors.mono200, thickness = 1.dp)
                DrawerButton(
                    text = "메인",
                    icon = R.drawable.ic_home,
                    iconColor = AppColors.mono700,
                    onClick = {
                        HealthOnePage.pageTitle.value = "메인"
                        navController.navigate(PageRoutes.OverView.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                )
                Divider(color = AppColors.mono200, thickness = 1.dp)
                DrawerButton(
                    text = "심박수",
                    icon = R.drawable.ic_heart,
                    iconColor = Color.Unspecified,
                    onClick = {
                        HealthOnePage.pageTitle.value = "심박수"
                        navController.navigate(PageRoutes.HeartRate.route)
                    }
                )
                Divider(color = AppColors.mono200, thickness = 1.dp)
                DrawerButton(
                    text = "식단",
                    icon = R.drawable.ic_food,
                    iconColor = AppColors.orange500,
                    onClick = {
                        HealthOnePage.pageTitle.value = "식단"
                        navController.navigate(PageRoutes.MealPlan.route)
                    }
                )
                Divider(color = AppColors.mono200, thickness = 1.dp)
                DrawerButton(
                    text = "수면",
                    icon = R.drawable.ic_sleep,
                    iconColor = AppColors.mono900,
                    onClick = {
                        HealthOnePage.pageTitle.value = "수면"
                        navController.navigate(PageRoutes.Sleep.route)
                    }
                )
                Divider(color = AppColors.mono200, thickness = 1.dp)
                DrawerButton(
                    text = "걸음수",
                    icon = R.drawable.ic_walking_svg,
                    iconColor = AppColors.blue900,
                    onClick = {
                        HealthOnePage.pageTitle.value = "걸음수"
                        navController.navigate(PageRoutes.Walking.route)
                    }
                )
                Divider(color = AppColors.mono200, thickness = 1.dp)
                DrawerButton(
                    text = "건강상태",
                    icon = R.drawable.ic_health_info,
                    iconColor = Color.Unspecified,
                    onClick = {
                        HealthOnePage.pageTitle.value = "건강정보"
                        navController.navigate(PageRoutes.HealthStatus.route)
                    }
                )
                Divider(color = AppColors.mono200, thickness = 1.dp)
                DrawerButton(
                    text = "챌린지",
                    icon = R.drawable.ic_fire,
                    iconColor = AppColors.red900,
                    onClick = {
                        HealthOnePage.pageTitle.value = "챌린지"
                        navController.navigate(PageRoutes.Challenge.route)
                    }
                )
                Divider(color = AppColors.mono200, thickness = 1.dp)
                DrawerButton(
                    text = "설정",
                    icon = R.drawable.ic_setting,
                    iconColor = Color.Unspecified,
                    onClick = {
                        HealthOnePage.pageTitle.value = "설정"
                        navController.navigate(PageRoutes.Setting.route)
                    }
                )
                Divider(color = AppColors.mono200, thickness = 1.dp)
            }
        }
}