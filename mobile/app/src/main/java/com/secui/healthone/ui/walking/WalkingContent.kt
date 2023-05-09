package com.secui.healthone.ui.walking

import android.app.appsearch.SearchResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.secui.healthone.viewmodel.ContentViewModel

@Composable
fun WalkingContent() {
    val viewModel: ContentViewModel = viewModel()
    val videos = viewModel.videos.value

    LazyRow {
        items(videos) { video ->
            Contents(video)
        }
    }
}
