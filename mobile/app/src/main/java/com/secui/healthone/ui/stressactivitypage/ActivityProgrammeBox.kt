package com.secui.healthone.ui.stressactivitypage

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.R;

@Composable
fun ActivityProgrammeBox(
    modifier: Modifier = Modifier
){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp)) {

        // 프로그램 제목 등
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp, 8.dp)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = ActivityProgrammeBoxText.programmeTtitle,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.width(16.dp));

            Text(text = ActivityProgrammeBoxText.programmeName,
                fontSize = 16.sp
            );
        }

        // 프로그램 소개 정보
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp, 8.dp)
        ) {

            Text(text = ActivityProgrammeBoxText.programmeOverviewTitle,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp));

            Text(text = ActivityProgrammeBoxText.programmeOverviewContent,
                fontSize = 14.sp,
                color = colorResource(
                id = R.color.mono700
                )
            )
        }

    }
}

class ActivityProgrammeBoxText {
    companion object {
        const val programmeTtitle = "프로그램 명 : "
        const val programmeName = "조깅(Jogging)"
        const val programmeOverviewTitle = "프로그램 소개"
        const val programmeOverviewContent = "스트레스 가득한 하루, 기분 전환 겸 가벼운 조깅 어떠세요?\n" +
                "가볍게 몸을 움직이며 내 마음에 쌓인 스트레스가 자연스럽게 풀리는 것은 덤!\n" +
                "\n" +
                "장소는 어디든 좋습니다.\n" +
                "당신이 가고 싶은 곳이라면 어디든 지금 바로 간단한 조깅 한번 해볼까요?\n"

    }
}