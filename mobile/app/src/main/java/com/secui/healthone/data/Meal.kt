package com.secui.healthone.data

data class Meal(
    val name: String,
    val description: String,
    val calories: Int,
    val imageUrl: String? = null
)
