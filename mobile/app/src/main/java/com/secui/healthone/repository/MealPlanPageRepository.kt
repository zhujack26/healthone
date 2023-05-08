package com.secui.healthone.repository

<<<<<<< HEAD
import kotlinx.coroutines.delay
import java.util.Calendar

suspend fun fetchCaloriesData(selectedDate: Calendar): CaloriesData {
    // 이 부분에 실제 API 호출을 처리하는 코드를 작성합니다.
    //예시
    var Calorie1 = 3400 // 섭취
    // Calorie1 = api.intakeCalories
    var Calorie2 = 1500 //소모
    // Calorie2 = api.burnedCalories
    var Calorie3 = Calorie1 - Calorie2 //총 칼로리
    // 남여, 나이에 따라 권장 칼로리 설정
    var Calorie4 = 2500 //적정 칼로리
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
=======
import com.secui.healthone.viewmodel.CaloriesApi
import java.util.Calendar
>>>>>>> 48ed6455598a786f90b976d86faa3b9addc1deef

data class CaloriesData(
    val intakeCalories: Int,
    val burnedCalories: Int,
    val totalCalories: Int,
    val recommendedCalories: Int
)

data class CaloriesApiResponse(
    val timestamp: String,
    val message: String,
    val data: CaloriesDataResponse,
    val success: Boolean
)

data class CaloriesDataResponse(
    val userNo: Int,
    val createTime: String,
    val sumKcalConsume: Int,
    val sumKcalEaten: Int
)

suspend fun fetchCaloriesData(date: String): CaloriesData? {
    val dateString = "${date}T00:00:00"
    return try {
        val apiResponse = fetchCalories(dateString)
        val intakeCalories = apiResponse.data.sumKcalEaten
        val burnedCalories = apiResponse.data.sumKcalConsume
        val totalCalories = intakeCalories - burnedCalories
        val recommendedCalories = 2500 // 적절한 권장 칼로리 계산 로직을 추가합니다.

        CaloriesData(
            intakeCalories = intakeCalories,
            burnedCalories = burnedCalories,
            totalCalories = totalCalories,
            recommendedCalories = recommendedCalories
        )
    } catch (e: Exception) {
        CaloriesData(
            intakeCalories = 0,
            burnedCalories = 0,
            totalCalories = 0,
            recommendedCalories = 0
        )
    }
}



suspend fun fetchCalories(date: String): CaloriesApiResponse {
    val caloriesApi = CaloriesApi.create()
    val response = caloriesApi.getCalories(date)

    if (response.isSuccessful) {
        return response.body() ?: throw Exception("Failed to fetch calories data")
    } else {
        throw Exception("Failed to fetch calories data: ${response.errorBody()?.string()}")
    }
}
