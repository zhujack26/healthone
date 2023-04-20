package com.secui.healthone.repository

import kotlinx.coroutines.delay

suspend fun fetchCaloriesData(): CaloriesData {
    // 이 부분에 실제 API 호출을 처리하는 코드를 작성합니다.
    //예시
    var Calorie1 = 3200
    // Calorie1 = api.intakeCalories
    var Calorie2 = 1000
    // Calorie2 = api.burnedCalories
    var Calorie3 = Calorie1 - Calorie2
    // 남여, 나이에 따라 권장 칼로리 설정
    var Calorie4 = 2500
    //  when(api.gender)
    //        "female" -> Calorie4 = 354 - 6.91 * age + 1.2 * (9.36 * weight + 726 * height)
    //        "male" -> Calorie4 = 662 - 9.53 * age + 1.2 * (15.9 * weight + 539.6 * height)
    //else -> throw IllegalArgumentException("Invalid gender input. Accepted values: 'female' or 'male'.")
    return CaloriesData(
        intakeCalories = Calorie1,
        burnedCalories = Calorie2,
        totalCalories = Calorie3,
        recommendedCalories = Calorie4
    //
    )
}

data class CaloriesData(
    val intakeCalories: Int,
    val burnedCalories: Int,
    val totalCalories: Int,
    val recommendedCalories: Int
)