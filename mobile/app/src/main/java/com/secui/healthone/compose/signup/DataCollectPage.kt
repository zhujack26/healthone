package com.secui.healthone.compose.signup

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.ui.datacollectpage.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.secui.healthone.constant.PageRoutes
import com.secui.healthone.data.HealthInfo
import com.secui.healthone.instance.HealthInfoInstance
import com.secui.healthone.ui.datacollectpage.ImageUri.saveNicknameToPrefs
import com.secui.healthone.viewmodel.HealthInfoViewModel
import com.secui.healthone.viewmodel.HealthInfoViewModelFactory
import com.secui.healthone.viewmodel.UserViewModel
import java.time.Instant


@Composable
fun DataCollectFirstPage(navController: NavController, userViewModel: UserViewModel) {

    val context = LocalContext.current
    val (nickname, setNickname) = remember { mutableStateOf("") }
    val (showDialogState, setShowDialogState) = remember { mutableStateOf(false) }  // Initialize to false

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        item {
            // 인덱스 컴포넌트
            Row {
                Index(number = 1, filled = true)
                Spacer(modifier = Modifier.width(16.dp))
                Index(number = 2, filled = false)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "건강 분석에 필요한 설정",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "1분이면 끝나요",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(32.dp))

            // 프로필 사진 추가 컴포넌트
            Row(verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "프로필 사진",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                PhotoPicker()
            }
            Spacer(modifier = Modifier.height(16.dp))
            // 성별 컴포넌트
            GenderSelection { selectedGender ->
                userViewModel.gender.value = selectedGender ?: false
            }
            Spacer(modifier = Modifier.height(16.dp))
            // 닉네임 컴포넌트
            NicknameInput(nicknameState = nickname, onNicknameChange = setNickname)
            Spacer(modifier = Modifier.height(16.dp))

            // 생년월일 컴포넌트
            val birthDate = remember { mutableStateOf("") }
            BirthDate(
                value = birthDate.value,
                onValueChange = { newDate ->
                    birthDate.value = newDate
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            // 신장 컴포넌트
            Height(userViewModel = userViewModel)
            Spacer(modifier = Modifier.height(16.dp))
            // 체중 컴포넌트
            Weight(userViewModel = userViewModel)
            Spacer(modifier = Modifier.height(32.dp))
            // 다음 버튼 컴포넌트
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                NextButton(navController, onClick = {
                    val isInvalidNickname = nickname.isBlank() || nickname.length > 9 || !nickname.matches(Regex("^(?=.*[ㄱ-힣a-zA-Z])[ㄱ-힣a-zA-Z]{1,9}$"))
                    if (isInvalidNickname) {
                        setShowDialogState(true)
                    } else {
                        saveNicknameToPrefs(context, nickname)
                        userViewModel.nickname.value = nickname
                        userViewModel.birthdate.value = birthDate.value
                        navController.navigate(PageRoutes.DataCollectSecond.route)
                    }
                }, showDialog = showDialogState, setShowDialog = setShowDialogState)
            }

            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}
@Composable
fun DataCollectSecondPage(navController: NavController, userViewModel: UserViewModel) {
    Log.d("DataCollectSecondPage", "Received nickname: ${userViewModel.nickname.value}")
    val workRate = remember { mutableStateOf<String?>(null) }
    val healthInfoApi = HealthInfoInstance.api
    val viewModelFactory = HealthInfoViewModelFactory(healthInfoApi)
    val viewModel: HealthInfoViewModel = viewModel(factory = viewModelFactory)

    // Context
    val context = LocalContext.current

    // 암호화된 SharedPreferences 인스턴스
    val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
    val sharedPreferences = EncryptedSharedPreferences.create(
        "secret_shared_prefs",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    // accessToken을 가져오기
    val accessToken = sharedPreferences.getString("access_token", "") ?: ""

    // 사용자 입력 값 저장

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        item {
            // 인덱스 컴포넌트
            Row {
                Index(number = 1, filled = false)
                Spacer(modifier = Modifier.width(16.dp))
                Index(number = 2, filled = true)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "목표를 설정해보세요",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(32.dp))
            // 목표 운동량 컴포넌트
            Column(modifier = Modifier
                .padding(16.dp)) {
                Text(
                    text = "활동량",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(8.dp))
                ExcersiseAmount(userViewModel)
            }
            Spacer(modifier = Modifier.height(32.dp))
            // 목표 걸음수 컴포넌트
            Column(modifier = Modifier
                .padding(16.dp)) {
                Text(
                    text = "일일 걸음수",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(8.dp))
                StepGoal(userViewModel)
            }
            Spacer(modifier = Modifier.height(16.dp))
            // 목표 수면시간 컴포넌트
            Column(modifier = Modifier
                .padding(16.dp)) {
                Text(
                    text = "수면",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(8.dp))
                val context = LocalContext.current
                SleepGoal(userViewModel, context)
            }
            Spacer(modifier = Modifier.height(32.dp))
            // 완료버튼 컴포넌트
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                NextSecondButton(navController) {
                    val nickname = userViewModel.nickname.value
                    val gender = userViewModel.gender.value
                    val birthdate = userViewModel.birthdate.value
                    val height = userViewModel.height.value.toIntOrNull() ?: 170
                    val weight = userViewModel.weight.value.toIntOrNull() ?: 70
                    val stepGoal = userViewModel.stepGoal.value
                    val sleepTime = userViewModel.sleepTime.value
                    val wakeUpTime = userViewModel.wakeUpTime.value
                    val workRate = userViewModel.workRate.value

                    Log.d("DataCollectSecondPage"
                        , "nickname : $nickname, gender : $gender, birthdae : $birthdate, " +
                                "height : $height, weight : $weight")
                    val accessToken = accessToken
                    val healthInfo = HealthInfo(
                        nickname = nickname,
                        gender = gender,
                        birthdate = birthdate,
                        height = height,
                        weight = weight,
                        workRate = workRate,
                        stepGoal = stepGoal,
                        sleepTime = if (sleepTime != null && sleepTime.isNotBlank()) sleepTime else "22:00:00",
                        wakeUpTime = if (wakeUpTime != null && wakeUpTime.isNotBlank()) wakeUpTime else "08:00:00"
                    )

                    viewModel.updateHealthInfo(accessToken, healthInfo)

                }
            }
            Spacer(modifier = Modifier.height(32.dp))

        }
    }
}