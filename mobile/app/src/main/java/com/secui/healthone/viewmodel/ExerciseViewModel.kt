package com.secui.healthone.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import com.secui.healthone.api.ExerciseApi
import com.secui.healthone.data.MealPlan.ExerciseList
import com.secui.healthone.data.MealPlan.ExerciseSearch
import com.secui.healthone.data.MealPlan.AddExercise
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ExerciseViewModel : ViewModel() {
    private val exerciseApi = ExerciseApi.create()
    val exerciseSearchList = MutableLiveData<List<ExerciseSearch>>()
    var searchTerm = ""
    val exerciseDataList = MutableLiveData<List<ExerciseList>>()
    val searchResults = MutableLiveData<List<ExerciseSearch>>()


    suspend fun addExercise(Exercise: AddExercise) {
        Log.d("ExerciseViewModel", "addExercise called with exercise: $Exercise")
        val gson = GsonBuilder()
            .registerTypeAdapter(
                LocalDateTime::class.java,
                JsonSerializer<LocalDateTime> { src, _, _ ->
                    JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                })
            .create()
        try {
            val response = exerciseApi.addExercise(Exercise)
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

    fun searchExercise(query: String) {
        searchTerm = query
        viewModelScope.launch {
            val response = exerciseApi.searchExercise(query)
            if (response.isSuccessful) {
                val responseBody = response.body()
                val newResults = responseBody?.data ?: emptyList<ExerciseSearch>()
                searchResults.value = newResults
                Log.d("ExerciseViewModel", "searchResults: $newResults") // 로그 추가
            }
        }
    }


    fun getExerciseList(date: String) {
        viewModelScope.launch {
            val inputFormatter = DateTimeFormatter.ofPattern("yyyy-M-d'T'HH:mm:ss")
            val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formattedDate =
                LocalDateTime.parse(date, inputFormatter).format(outputFormatter)
            val response = exerciseApi.getExerciseList(formattedDate)
            Log.d("MealViewModel", "getMealList response: $response")
            if (response.isSuccessful) {
                exerciseDataList.value = response.body()?.data ?: emptyList()
            } else {
                // handle the error case
                Log.e(
                    "MealViewModel",
                    "Error getting meal list: ${response.errorBody()?.string()}"
                )
            }
        }
    }
    fun deleteExercise(exerciseNo: Int, date: String, onRefreshGraph: () -> Unit) {
        viewModelScope.launch {
            try {
                val response: Response<Unit> = exerciseApi.deleteExercise(exerciseNo)
                if (response.isSuccessful) {
                    // 요청이 성공한 경우 처리할 코드를 여기에 작성하세요.
                    getExerciseList(date)
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
