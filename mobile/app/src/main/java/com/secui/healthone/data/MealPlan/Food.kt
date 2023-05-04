package com.secui.healthone.data.MealPlan

import com.google.gson.annotations.SerializedName

data class FoodData(
    val no: Int,
    val name: String,
    val kcal: Double,
    val gram: Double
)


data class MealData(
    @SerializedName("no")
    val no: Int,
    @SerializedName("userNo")
    val userNo: Int,
    @SerializedName("food")
    val food: Food?,
    @SerializedName("customfood")
    val customFood: CustomFood?,
    @SerializedName("createTime")
    val createTime: String,
    @SerializedName("mealType")
    val mealType: String,
    @SerializedName("portion")
    val portion: Int,
    @SerializedName("gram")
    val gram: Int,
    @SerializedName("kcal")
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
    val gram: Int
)

data class CustomFood(
    @SerializedName("no")
    val no: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("kcal")
    val kcal: Int,
    @SerializedName("gram")
    val gram: Int
)
data class MealResponse(
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<MealData>,
    @SerializedName("success")
    val success: Boolean
)
