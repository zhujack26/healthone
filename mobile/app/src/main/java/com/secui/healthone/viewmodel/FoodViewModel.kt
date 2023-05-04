package com.secui.healthone.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secui.healthone.data.MealPlan.Food
import com.secui.healthone.api.MealApi
import com.secui.healthone.data.MealPlan.FoodRepository
import com.secui.healthone.data.MealPlan.MealData
import kotlinx.coroutines.launch

class FoodViewModel : ViewModel() {
    private val foodApi = MealApi.FoodApi.create()
    private val foodRepository = FoodRepository(foodApi)

    val searchResults = MutableLiveData<List<Food>>()

    fun searchFood(searchTerm: String) {
        viewModelScope.launch {
            val response = foodRepository.searchFood(searchTerm)
            if (response.isSuccessful) {
                searchResults.value = response.body()?.data ?: emptyList()
            } else {
                // handle the error case
            }
        }
    }
}

class MealViewModel : ViewModel() {
    private val mealApi = MealApi.create()
    val mealDataList = MutableLiveData<List<MealData>>()

    fun getMealList(date: String, userNo: Int) {
        viewModelScope.launch {
            val response = mealApi.getMealList(date, userNo)
            if (response.isSuccessful) {
                mealDataList.value = response.body()?.data ?: emptyList()
            } else {
                // handle the error case
            }
        }
    }
}
