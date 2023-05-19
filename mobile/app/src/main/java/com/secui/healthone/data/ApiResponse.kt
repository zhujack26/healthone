package com.secui.healthone.data

data class ApiResponse<T>(
    val timestamp: String,
    val message: String,
    val data: T,
    val success: Boolean
)
