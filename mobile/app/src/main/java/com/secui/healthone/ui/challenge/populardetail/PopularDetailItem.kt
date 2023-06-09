package com.secui.healthone.ui.challenge.populardetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.secui.healthone.R
import com.secui.healthone.constant.AppColors
import com.secui.healthone.constant.HealthOnePage
import com.secui.healthone.viewmodel.ChallenegeViewModel

@Composable
fun PopularDetailItem(
    modifier: Modifier = Modifier
) {
    val challnegeInfo =  ChallenegeViewModel.currentChallenge.value;
    val isEmpthyChallenge = challnegeInfo == null;

    HealthOnePage.pageTitle.value = challnegeInfo?.name ?: ""

    val openDialog = remember { mutableStateOf(false) }
    // 다이얼로그
    if(openDialog.value &&
        !isEmpthyChallenge &&
        challnegeInfo?.sportEquipmentCheck == true){
        ToolsDialog(openDialog, equipment = challnegeInfo?.equipment.toString())
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
        horizontalAlignment = Alignment.Start
    ){
        Text(
            text = challnegeInfo?.name ?: "",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = challnegeInfo?.introduce ?: "",
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
            text = stringResource(id = R.string.challenge_over_view_text),
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
                    text = if(isEmpthyChallenge)
                                0.toString()
                            else challnegeInfo?.totalWorkCount.toString(),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.total_times_text),
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
                    text = if(isEmpthyChallenge)
                                0.toString()
                            else challnegeInfo?.totalPeriod.toString(),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.total_period_text),
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
                    text = stringResource(R.string.programme_level_text),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = challnegeInfo?.level.toString(),
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
                    text = stringResource(R.string.average_text),
                    fontSize = 16.sp
                ) // category
                Text(
                    text =
                    if(isEmpthyChallenge)
                        "-"
                    else challnegeInfo?.avgWorkTime.toString(),
                    fontSize = 16.sp
                ) // value

            }
            Spacer(modifier = Modifier.height(16.dp))
            // 운동 기구
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.tools_text),
                    fontSize = 16.sp
                ) // category
               Row(modifier = Modifier
                   .wrapContentWidth()
                   .wrapContentHeight(),
                   verticalAlignment = Alignment.CenterVertically,
               ) {
                   Text(
                       text = if(isEmpthyChallenge) "없음"
                                else if(challnegeInfo?.sportEquipmentCheck == true) "필요"
                                else "불필요",
                       fontSize = 16.sp
                   ) // value
                   Spacer(modifier = Modifier.width(4.dp))
                   if(challnegeInfo?.sportEquipmentCheck == true){
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
                    text = stringResource(R.string.progamme_type),
                    fontSize = 16.sp
                ) // category
                Text(
                    text = challnegeInfo?.programType.toString(),
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
                    text = stringResource(R.string.recommend_day),
                    fontSize = 16.sp
                ) // category
                Text(
                    text = if(isEmpthyChallenge) "-"
                            else challnegeInfo?.recommendWeek.toString(),
                    fontSize = 16.sp
                ) // value
            }
            Spacer(modifier = Modifier.height(32.dp))
            // guide text
            Text(text = stringResource(R.string.recommend_guide_text),
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
            text =  stringResource(R.string.see_video_btn_text),
            fontSize = 16.sp,
            color = AppColors.white
        )
    }
    Spacer(modifier = Modifier.height(96.dp))

}

class PopularDetailItemText {
    companion object {
    }
}


// 필요 운동 도구 알람창
@Composable
fun ToolsDialog(openDialog:MutableState<Boolean>, equipment:String) {
    Dialog(onDismissRequest = { openDialog.value = false }) {
        Surface(modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight(),
            shape = RoundedCornerShape(12.dp),
            color = AppColors.white
        ) {
            ToolsInfoDialog(openDialog, equipment=equipment)
        }
    }
}

@Composable
fun ToolsInfoDialog(openDialog:MutableState<Boolean>, equipment:String){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.programme_need_tooL_text),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = equipment,
            fontSize = 16.sp,
            color = AppColors.mono700,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(R.string.check_btn_text),
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

