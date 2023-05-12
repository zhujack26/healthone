package com.secui.healthone.viewmodel

import androidx.lifecycle.ViewModel
import com.secui.healthone.repository.CaloriesApiResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class MealPlanViewModel : ViewModel() {
    // 여기에 식단 관리 페이지와 관련된 상태를 관리하는 코드를 작성합니다.

}
interface CaloriesApi {
    @GET("api/calorie")
    suspend fun getCalories(
        @Query("date") date: String
    ): Response<CaloriesApiResponse>

    companion object {
        private const val BASE_URL = "http://a80d3a967a5514702bfe8ba3e8b52871-1335940738.ap-northeast-2.elb.amazonaws.com/"

        fun create(): CaloriesApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CaloriesApi::class.java)
        }
    }
}
