package com.secui.healthone.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.secui.healthone.data.*

interface YouTubeService {
    @GET("search")
    suspend fun searchVideos(
        @Query("part") part: String = "snippet",
        @Query("q") query: String,
        @Query("type") type: String = "video",
        @Query("maxResults") maxResults: Int = 5,
        @Query("key") key: String = "AIzaSyAtZQGhWIiLRVNcQ9sUYUP6rb0HHjZFA6g"
    ): Response<YouTubeSearchResponse>
}