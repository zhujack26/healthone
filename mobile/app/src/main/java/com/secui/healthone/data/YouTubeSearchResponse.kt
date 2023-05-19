package com.secui.healthone.data

data class YouTubeSearchResponse(
    val items: List<YouTubeVideoItem>
)

data class YouTubeVideoItem(
    val id: YouTubeVideoId,
    val snippet: YouTubeVideoSnippet
)

data class YouTubeVideoId(
    val videoId: String
)

data class YouTubeVideoSnippet(
    val title: String,
    val thumbnails: YouTubeVideoThumbnails
)

data class YouTubeVideoThumbnails(
    val default: YouTubeVideoThumbnail
)

data class YouTubeVideoThumbnail(
    val url: String
)