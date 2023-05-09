package com.secui.healthone.compose.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.secui.healthone.service.YouTubeService
import com.secui.healthone.viewmodel.ContentViewModel

class YouTubeViewModelFactory(private val youtubeService: YouTubeService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContentViewModel::class.java)) {
            return ContentViewModel(youtubeService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}