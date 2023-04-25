package com.secui.healthone.ui.stressindexpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.secui.healthone.R

@Composable
fun StressRecommendBox(
//    navController: NavController,
    modifier: Modifier=Modifier
){

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp))
    {
        Text(text = StressRecommendBoxText.titleString)

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .horizontalScroll(rememberScrollState())
            .padding(8.dp)
        )
        {
            repeat(8){
                // recommand Item으로 분할하기!
//                Image(
//                    painter = painterResource(id = R.drawable.recommand_sample1),
//                    contentDescription = StressRecommendBoxText.imgDescString,
//                    modifier = Modifier
//                        .width(128.dp)
//                        .height(128.dp),
//                    contentScale = ContentScale.Crop,
//                )
                RecommandItem();
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

    }

}

class StressRecommendBoxText{
    companion object {
        const val titleString = "당신을 위한 활동 추천"
        const val imgDescString = "컨텐츠 추천 이미지"
    }
}