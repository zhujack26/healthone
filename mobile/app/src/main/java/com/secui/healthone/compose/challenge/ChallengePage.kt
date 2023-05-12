package com.secui.healthone.compose

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.secui.healthone.compose.factory.YouTubeViewModelFactory
import com.secui.healthone.data.ChallengeInfo
import com.secui.healthone.data.Video
import com.secui.healthone.data.heart.HeartRead
import com.secui.healthone.repository.ChallengeRepository
import com.secui.healthone.repository.HeartRateRepository
import com.secui.healthone.service.YouTubeService
import com.secui.healthone.ui.challenge.challengepage.ChallengeContentBox
import com.secui.healthone.ui.challenge.challengepage.ChallengePopularBox
import com.secui.healthone.ui.challenge.challengepage.ChallengeRankItem
import com.secui.healthone.viewmodel.ChallenegeViewModel
import com.secui.healthone.viewmodel.ContentViewModel
import com.secui.healthone.viewmodel.HeartRateViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChallengePage(
    navController: NavHostController,
    modifier: Modifier=Modifier)
{

    val mOwner = LocalLifecycleOwner.current
    val repository = ChallengeRepository();
    val viewModel = ChallenegeViewModel(repository)
    val challengeList: MutableState<MutableList<ChallengeInfo>> = remember { mutableStateOf(mutableListOf()) }

    viewModel.getChallengeList();
    viewModel.challengeList.observe(mOwner, Observer{
        //Log.d("HEART_PAGE:::", "${it.toString()}")
        challengeList.value = it;
    })

    ChallenegeViewModel.challengeList.value = challengeList.value;

    // youtube로부터 요청 전송

    // YouTube API에 대한 Retrofit 인스턴스 생성
    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/youtube/v3/") // YouTube API base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val youtubeService = retrofit.create(YouTubeService::class.java)
    val factory = YouTubeViewModelFactory(youtubeService)

    val contentViewModel: ContentViewModel = viewModel(factory = factory)

    val videos:List<Video> = contentViewModel.videos.value
    LaunchedEffect(Unit) {
        contentViewModel.searchVideos("헬스")
    }

    Column(modifier= Modifier
        .fillMaxSize()
        .verticalScroll
            (rememberScrollState())
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        ChallengePopularBox(navController, challengeList = challengeList.value)
        Spacer(modifier = Modifier.height(32.dp))
        ChallengeContentBox(navController, videos=videos)
        Spacer(modifier = Modifier.height(16.dp))
        ChallengeRankItem(navController)

    }
}