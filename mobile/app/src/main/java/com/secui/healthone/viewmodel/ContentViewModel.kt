package com.secui.healthone.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secui.healthone.data.YouTubeSearchResponse
import com.secui.healthone.data.Video
import com.secui.healthone.service.YouTubeService
import kotlinx.coroutines.launch
import retrofit2.Response

class ContentViewModel(private val youTubeService: YouTubeService) : ViewModel() {

    val videos = mutableStateOf<List<Video>>(emptyList())

    init {
        viewModelScope.launch {
            val response = youTubeService.searchVideos(query = "walking")
            if (response.isSuccessful) {
                val searchResponse = response.body()
                videos.value = searchResponse?.items?.map {
                    Video(
                        id = it.id.videoId,
                        title = it.snippet.title,
                        thumbnailUrl = it.snippet.thumbnails.default.url
                    )
                } ?: emptyList()
            }
        }
    }
}