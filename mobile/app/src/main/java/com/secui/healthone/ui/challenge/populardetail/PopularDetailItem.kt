package com.secui.healthone.ui.challenge.populardetail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.secui.healthone.R
import com.secui.healthone.ui.common.AppColors
import com.secui.healthone.util.PageRoutes

@Composable
fun PopularDetailItem(
    modifier: Modifier = Modifier
) {
    val openDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current;

    // 다이얼로그
    if(openDialog.value){
        ToolsDialog(openDialog)
    }
    
    Image(
        painter = painterResource(id = R.drawable.recommand_sample1),
        contentDescription = "운동 추천" ,
        modifier = Modifier
            .fillMaxWidth()
            .height(196.dp),
        contentScale = ContentScale.Crop
    )
    Spacer(modifier = Modifier.height(16.dp))

    // introduce
    Column(modifier= Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp),
        horizontalAlignment = Alignment.Start,
    )
    {
        Text(
            text = PopularDetailItemText.challengeName,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = PopularDetailItemText.challengeDescription,
            fontSize = 16.sp,
            color = AppColors.mono700
        )
    }

    //over view
    Column(modifier= Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp),
        horizontalAlignment = Alignment.Start,
    )
    {
        // title
        Text(
            text = PopularDetailItemText.challengeOverViewText,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(32.dp))
        // 프로그램 개요
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // total times
            Column(modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = PopularDetailItemText.totalTimeValue,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = PopularDetailItemText.totalTimesText,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.width(32.dp))
            // total period
            Column(modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = PopularDetailItemText.totalPeriodValue,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = PopularDetailItemText.totalPeriodText,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.width(32.dp))
            // challenge level
            Column(modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = PopularDetailItemText.levelValue,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = PopularDetailItemText.levelText,
                    fontSize = 16.sp
                )

            }
        }
        Spacer(modifier = Modifier.height(64.dp))
        // detail challenge information
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
        ) {

            // detail info lines
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = PopularDetailItemText.averageText,
                    fontSize = 16.sp
                ) // category
                Text(
                    text = PopularDetailItemText.averageValue,
                    fontSize = 16.sp
                ) // value

            }
            Spacer(modifier = Modifier.height(16.dp))
            // 운동 기고
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = PopularDetailItemText.toolsText,
                    fontSize = 16.sp
                ) // category
               Row(modifier = Modifier
                   .wrapContentWidth()
                   .wrapContentHeight(),
                   verticalAlignment = Alignment.CenterVertically,
               ) {
                   Text(
                       text = PopularDetailItemText.isToolNeed,
                       fontSize = 16.sp
                   ) // value
                   Spacer(modifier = Modifier.width(4.dp))
                   Image(
                       painter = painterResource(id = R.drawable.ic_info),
                       contentDescription = "운동기구 타입 아이콘",
                       modifier= Modifier
                           .width(16.dp)
                           .height(16.dp)
                           .clickable {
                               openDialog.value = true;
                           },
                       contentScale = ContentScale.Fit,
                   )
               }

            }
            Spacer(modifier = Modifier.height(16.dp))
            // 프로그램 유형
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = PopularDetailItemText.progammeType,
                    fontSize = 16.sp
                ) // category
                Text(
                    text = PopularDetailItemText.progammeTypeValue,
                    fontSize = 16.sp
                ) // value
            }
            Spacer(modifier = Modifier.height(16.dp))

            // 추천 요일
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = PopularDetailItemText.recommendDay,
                    fontSize = 16.sp
                ) // category
                Text(
                    text = PopularDetailItemText.recommendDayValue,
                    fontSize = 16.sp
                ) // value
            }
            Spacer(modifier = Modifier.height(32.dp))
            // guide text
            Text(text = PopularDetailItemText.recommendGuideText,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
    Spacer(modifier = Modifier.height(64.dp))
    // Button
    Button(colors = ButtonDefaults
        .outlinedButtonColors(
            backgroundColor =
            AppColors.red100
        ),
        modifier = Modifier
            .width(256.dp)
            .height(48.dp)
        ,
        onClick = {
        }) {
        Text(
            text = PopularDetailItemText.seeVideoBtnText,
            fontSize = 16.sp,
            color = AppColors.white
        )
    }
    Spacer(modifier = Modifier.height(96.dp))

}

class PopularDetailItemText {
    companion object {
        const val challengeName = "특색 있는 운동, 요가 챌린지"
        const val challengeDescription = " 지루한 운동은 이제 그만, 요가를 통해 심신의 안정과 운동효과까지 모두 누려보세요. 이 코스만 따라하면 당신도 아름다운 몸매를 만드실 수 있어요. 도전해보세요!"

        const val challengeOverViewText = "프로그램 개요" // not to data

        const val totalTimeValue = "8"
        const val totalTimesText = "총 운동 횟수"

        const val totalPeriodValue = "2"
        const val totalPeriodText = "총 기간(주)"

        const val levelValue = "쉬움"
        const val levelText = "난이도"

        const val averageText = "평균 운동 시간"
        const val averageValue = "20분"

        const val toolsText = "운동 기구"
        const val isToolNeed = "필요"

        const val progammeType = "프로그램 유형"
        const val progammeTypeValue = "체중 감량"

        const val needToolText = "필요한 운동 기구"
        const val needToolValue = "요가매트, 짐볼, 풀업 바, 트레드밀"
        const val toolOkBtnText = "확인"

        const val recommendDay = "추천 운동 요일"
        const val recommendDayValue = "월, 수, 금"

        const val recommendGuideText = "상기 일정에 맞추어 운동에 도전해보세요"

        const val seeVideoBtnText = "운동 영상 보러가기"
    }
}

// temp

@Composable
fun ToolsDialog(openDialog:MutableState<Boolean>) {
    Dialog(onDismissRequest = { openDialog.value = false }) {
        Surface(modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight(),
            shape = RoundedCornerShape(12.dp),
            color = AppColors.white
        ) {
            ToolsInfoDialog(openDialog)
        }
    }
}

@Composable
fun ToolsInfoDialog(openDialog:MutableState<Boolean>){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp)

    ) {
        Text(
            text = PopularDetailItemText.needToolText,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = PopularDetailItemText.needToolValue,
            fontSize = 16.sp,
            color = AppColors.mono700,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = PopularDetailItemText.toolOkBtnText,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable {
                    openDialog.value = false;
                },
            textAlign = TextAlign.Center
        )
    }
}

