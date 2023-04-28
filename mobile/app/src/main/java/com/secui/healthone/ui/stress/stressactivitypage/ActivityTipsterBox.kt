package com.secui.healthone.ui.stress.stressactivitypage

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.R;
import com.secui.healthone.ui.common.AppColors

@Composable
fun ActivityTipsterBox(
    modifier: Modifier = Modifier
){

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp, 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(text = ActivityTipsterBoxText.tipsterNameTitle,
                fontSize = 12.sp,
                color = AppColors.mono900
            )
            Spacer(modifier = Modifier.width(4.dp));
            Text(text = ActivityTipsterBoxText.tipsterName,
                fontSize = 12.sp,
                color = AppColors.mono900
            )
        }

        Spacer(modifier = Modifier.height(16.dp));

        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp, 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {

            Text(text = ActivityTipsterBoxText.registDateTitle,
                fontSize = 12.sp,
                color = AppColors.mono900
            )
            Spacer(modifier = Modifier.width(4.dp));
            Text(text = ActivityTipsterBoxText.registDate,
                fontSize = 12.sp,
                color = AppColors.mono900
            )

        }

    }
}

class ActivityTipsterBoxText {
    companion object {
        const val tipsterNameTitle = "정보 제공자 : "
        const val tipsterName = "jogging-master0148"
        const val registDateTitle = "제공 일자 : "
        const val registDate = "2023. 04. 26."
    }
}