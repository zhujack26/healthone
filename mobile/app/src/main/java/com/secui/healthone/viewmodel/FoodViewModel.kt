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
import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import com.secui.healthone.api.FoodApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FoodViewModel : ViewModel() {
    private val foodApi = FoodApi.create()
    private val foodRepository = FoodRepository(foodApi)
    private val mealApi = MealApi.create()
    val hasMoreResults = MutableLiveData<Boolean>(false)
    val searchResults = MutableLiveData<List<Food>>()
    var currentPage = 1
    var totalResults = 0
    var searchTerm = ""

    suspend fun addMeal(meal: Meal) {
        Log.d("FoodViewModel", "addMeal called with meal: $meal")
        val gson = GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, JsonSerializer<LocalDateTime> { src, _, _ ->
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
                Log.e("FoodViewModel", "Request failed with code: ${response.code()} and message: ${response.message()}")
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
}



class MealViewModel : ViewModel() {
    private val mealApi = MealApi.create()
    val mealDataList = MutableLiveData<List<MealData>>()

    fun getMealList(date: String, userNo: Int) {
        viewModelScope.launch {
            val response = mealApi.getMealList(date, userNo)
            Log.d("MealViewModel", "getMealList response: $response")
            if (response.isSuccessful) {
                mealDataList.value = response.body()?.data ?: emptyList()
                Log.d("MealViewModel", "mealDataList: ${mealDataList.value}")
            } else {
                // handle the error case
                Log.e("MealViewModel", "Error getting meal list: ${response.errorBody()?.string()}") // 이 라인을 수정하세요
            }
        }
    }
}

