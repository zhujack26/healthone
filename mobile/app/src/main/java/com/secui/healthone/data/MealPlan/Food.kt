package com.secui.healthone.data.MealPlan

import com.google.gson.annotations.SerializedName


data class MealData(
    val no: Int,
    val userNo: Int,
    val name: String,
    val createTime: String,
    val mealType: String,
    val gram: Int,
    val kcal: Int
)

data class Food(
    @SerializedName("no")
    val no: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("kcal")
    val kcal: Int,
    @SerializedName("gram")
    val gram: Int,
    var isChecked: Boolean = false,
    var inputGrams: Int
)

data class MealResponse<T>(
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: T,
    @SerializedName("success")
    val success: Boolean
)
