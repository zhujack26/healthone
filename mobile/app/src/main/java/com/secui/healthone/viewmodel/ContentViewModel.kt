package com.secui.healthone.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secui.healthone.data.Video
import com.secui.healthone.service.YouTubeService
import kotlinx.coroutines.launch

class ContentViewModel(private val youTubeService: YouTubeService) : ViewModel() {

    val videos = mutableStateOf<List<Video>>(emptyList())

    fun searchVideos(query:String="걷기") {
        viewModelScope.launch {
            val response = youTubeService.searchVideos(query = query)
            if (response.isSuccessful) {
                val searchResponse = response.body()
                videos.value = searchResponse?.items?.map {
                    Video(
                        id = it.id.videoId,
                        title = it.snippet.title,
                        thumbnailUrl = it.snippet.thumbnails.default.url
                    )
                } ?: emptyList()
            } else {
                Log.e("ContentViewModel", "Error fetching videos: ${response.message()}")
                // Log error body
                val errorBody = response.errorBody()?.string() ?: "No error body"
                Log.e("ContentViewModel", "Error body: $errorBody")
            }
        }
    }
}