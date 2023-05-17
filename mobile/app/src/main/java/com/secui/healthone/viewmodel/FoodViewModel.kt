package com.secui.healthone.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secui.healthone.data.MealPlan.Food
import com.secui.healthone.api.MealApi
import com.secui.healthone.data.MealPlan.FoodRepository
import com.secui.healthone.data.MealPlan.Meal
import com.secui.healthone.data.MealPlan.MealData
import kotlinx.coroutines.launch
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FoodViewModel : ViewModel() {
    private val foodApi = MealApi.create()
    private val foodRepository = FoodRepository(foodApi)
    private val mealApi = MealApi.create()
    val hasMoreResults = MutableLiveData<Boolean>(false)
    val searchResults = MutableLiveData<List<Food>>()
    var currentPage = 1
    var totalResults = 0
    var searchTerm = ""
    private val mealViewModel = MealViewModel()

    suspend fun addMeal(meal: Meal) {
        Log.d("FoodViewModel", "addMeal called with meal: $meal")
        val gson = GsonBuilder()
            .registerTypeAdapter(
                LocalDateTime::class.java,
                JsonSerializer<LocalDateTime> { src, _, _ ->
                    JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                })
            .create()
        val mealJson = gson.toJson(meal)
        try {
            val response = mealApi.addMeal(meal)
            Log.d("FoodViewModel", "${response}")
            if (response.isSuccessful) {
                Log.d("FoodViewModel", "Request URL: ${response}")
            } else {
                Log.e(
                    "FoodViewModel",
                    "Request failed with code: ${response.code()} and message: ${response.message()}"
                )
            }
        } catch (e: Exception) {
            Log.e("FoodViewModel", "Error occurred while sending request: ", e)
        }
    }

    fun searchFood(query: String, page: Int = 1, size: Int = 20) {
        searchTerm = query
        viewModelScope.launch {
            val response = foodApi.searchFood(query, page, size)
            if (response.isSuccessful) {
                val responseBody = response.body()
                val newResults = responseBody?.data?.content ?: emptyList<Food>()
                currentPage = page
                totalResults = responseBody?.data?.totalResults ?: 0
                searchResults.value =
                    if (page == 1) newResults else searchResults.value.orEmpty() + newResults
                hasMoreResults.value = newResults.size == size
                currentPage += 1 // 페이지 번호를 증가시킵니다.
            }
        }
    }

    fun loadNextPage() {
        if (hasMoreResults.value == true) {
            searchFood(searchTerm, currentPage + 1)
        }
    }

    class MealViewModel : ViewModel() {
        private val mealApi = MealApi.create()
        val mealDataList = MutableLiveData<List<MealData>>()

        fun getMealList(date: String, userNo: Int) {
            viewModelScope.launch {
                val inputFormatter = DateTimeFormatter.ofPattern("yyyy-M-d'T'HH:mm:ss")
                val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val formattedDate =
                    LocalDateTime.parse(date, inputFormatter).format(outputFormatter)
                val response = mealApi.getMealList(formattedDate, userNo)
                Log.d("MealViewModel", "getMealList response: $response")
                if (response.isSuccessful) {
                    mealDataList.value = response.body()?.data ?: emptyList()
                    Log.d("MealViewModel", "mealDataList: ${mealDataList.value}")
                } else {
                    // handle the error case
                    Log.e(
                        "MealViewModel",
                        "Error getting meal list: ${response.errorBody()?.string()}"
                    )
                }
            }
        }
        fun deleteMeal(mealNo: Int, date: String, userNo: Int, onRefreshGraph: () -> Unit) {
            viewModelScope.launch {
                try {
                    val response: Response<Unit> = mealApi.deleteMeal(mealNo)
                    if (response.isSuccessful) {
                        // 요청이 성공한 경우 처리할 코드를 여기에 작성하세요.
                        getMealList(date, userNo)
                        onRefreshGraph() // 여기에서 콜백을 호출합니다.
                    } else {
                        // 오류 처리 코드를 여기에 작성하세요.
                    }
                } catch (e: Exception) {
                    // 네트워크 오류 처리 코드를 여기에 작성하세요.
                }
            }
        }
    }
}
