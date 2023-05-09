package com.secui.healthone.ui.heart.heartmeasurepage

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController
import com.secui.healthone.R;
import com.secui.healthone.data.heart.HeartWrite
import com.secui.healthone.repository.HeartRateRepository
import com.secui.healthone.ui.common.AppColors
import com.secui.healthone.util.PreferenceUtil
import com.secui.healthone.viewmodel.HeartRateViewModel



typealias fxType1 = () -> MutableState<Boolean>
typealias fxType2 = () -> Boolean;
@Composable
fun HeartMeasureItem(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
){
    val text = remember { mutableStateOf("") } // 입력 창과 맵핑할 mutableState
    val focusRequester by remember { mutableStateOf(FocusRequester()) } // 포커즈를 주기 위한 requester
    val focusManager = LocalFocusManager.current; // 포커즈 닫기를 위한 관리용 매니저
    val context:Context = LocalContext.current; // 현재 Context
    val prefs: PreferenceUtil = PreferenceUtil(context);


    //
    val mOwner = LocalLifecycleOwner.current

    val moveFx:fxType2 = {
        navHostController.popBackStack()
    }

    val writeHeartRate: fxType1 = {
        writeHeartRate(prefs = prefs, context = context, mOwner = mOwner, moveFunc=moveFx);

    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.heart_measure_item_title),
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(painter =
        painterResource(id = R.drawable.ic_heart),
            contentDescription = "하트 아이콘",
            modifier = Modifier
                .width(256.dp)
                .wrapContentHeight(),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(text = "${getBpmValue(text.value)}",
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = HeartMeasureItemText.HeartMeasureUnit,
                fontSize = 16.sp
            )

        }

        Spacer(modifier = Modifier.height(32.dp));

        TextField(
            value = text.value,
            label = { Text(stringResource(R.string.heart_input_label)) },
            onValueChange = { newText ->
                setDisplayBpmValue(
                    context, newText, focusManager,
                    text, prefs, navHostController,
                    writeHeartRate
                );
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = AppColors.black,
                backgroundColor = AppColors.white,
                focusedIndicatorColor = AppColors.red300,
                unfocusedIndicatorColor = AppColors.red100,
            ),
            modifier = Modifier
                .width(156.dp)
                .wrapContentHeight()
                .focusRequester(focusRequester=focusRequester)
//                .onFocusChanged { // 포커즈 줄 때 이벤트 리스너 연결할 일을 위해 일단 주석...
//                    it.isFocused
//                }
            ,
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Center,
                color = AppColors.black,
                fontSize = 16.sp
            ),
            keyboardOptions = KeyboardOptions
                .Default
                .copy(
                    keyboardType = KeyboardType.Number
                )
        )
        Spacer(modifier = Modifier.height(16.dp));
        Text(
            text = stringResource(R.string.heart_measure_guide),
            fontSize = 16.sp,
            color = AppColors.black,
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
        )
        Spacer(modifier = Modifier.height(32.dp));
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus() // 에딧 텍스트에 포커즈 요청
    }
}


fun setDisplayBpmValue(
    context:Context,
    newText: String,
    focusManager: FocusManager,
    text: MutableState<String>,
    prefs:PreferenceUtil,
    navHostController:NavHostController,
    func:fxType1
) {
    var realValue:String = getRealText(newText);
    val transInt = transToInt(realValue);

    if(transInt > 999){
        Toast.makeText(context, context.getString(R.string.err_bpm_guide), Toast.LENGTH_SHORT).show();
        realValue = ""
    }

    if(newText.length > 0 && newText[newText.length-1]=='\n'){
        focusManager.clearFocus()
        if(!realValue.isEmpty()) {
            prefs.setString("current_heart_bpm", transInt.toString())
            val result = func();
            Log.d("RESPONSE::::", "${result.value}");

        };
    }
    text.value = realValue;


//    if(transInt > 0 && transInt > 9 ){ // 유효한 숫자값이고 2자리 이상일 때,
//        if(transInt < 20 || transInt > 220){ // 유효 범위를 벗어 났다며느
//            var alertMessage:String? = "";
//            if(transInt < 20){
//                alertMessage = context.getString(R.string.less_bpm_guide)
//            }else {
//                alertMessage = context.getString(R.string.over_bpm_guide)
//            }
//            // 가이드 메세지 출력
//            Toast.makeText(context, alertMessage, Toast.LENGTH_SHORT).show();
//            text.value = ""
//        }
//    }


}

fun transToInt(realValue: String): Int {
    val value:Int? = realValue.toIntOrNull();
    return if(value==null) -1 else value;
}


// TextField 에서 사용할 메서드
fun getRealText(newText: String): String {
    val text = newText
        .replace("\n","")
        .replace(" ", "");
    val result:Int? = text.toIntOrNull();
    return if(result == null) "" else result.toString();
}

// 심박수를 표시하기 위한 문자열을 변환해줄 메서드
// 0이나 이상한 값이 오면 - 로 변환해주기 위함
fun getBpmValue(value:String):String{
    val bpmString = value.toIntOrNull();
    return if(bpmString==null) "-" else bpmString.toString();
}

class HeartMeasureItemText {
    companion object {
        const val HeartMeasureUnit = "bpm"
    }
}

fun writeHeartRate(context: Context, prefs:PreferenceUtil, mOwner:LifecycleOwner, moveFunc:fxType2):MutableState<Boolean>{
    val repository = HeartRateRepository()
    val viewModel = HeartRateViewModel(repository)
    val writeResult:MutableState<Boolean> =  mutableStateOf(false);

    val bpmValue = prefs.getString("current_heart_bpm", "0");
    viewModel.writeHeartRate(HeartWrite(count = bpmValue.toInt()));
    viewModel.heartWriteRespone.observe(mOwner, Observer {
        writeResult.value = it;
        if(it){
            Toast.makeText(context, "심박수 기록에 성공하였습니다", Toast.LENGTH_SHORT).show();
            moveFunc();
        }

    });

    return writeResult
}
