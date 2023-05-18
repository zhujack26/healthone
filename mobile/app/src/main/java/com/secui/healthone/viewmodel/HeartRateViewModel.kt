package com.secui.healthone.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secui.healthone.data.heart.HeartRead
import com.secui.healthone.data.heart.HeartWrite
import com.secui.healthone.repository.HeartRateRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import java.util.Objects

class HeartRateViewModel(private val repository : HeartRateRepository) : ViewModel() {
    val heartListResponse: MutableLiveData<MutableList<HeartRead>> = MutableLiveData() // post >> json place holder
    val heartWriteRespone:MutableLiveData<Boolean> = MutableLiveData(false);

    // 심박수 쓰기
    fun writeHeartRate(heartWrite: HeartWrite){
        viewModelScope.launch(exceptionHandler) {
            val response = repository.writeHeartRate(heartWrite);
            Log.d("RESPONSE VM:::", "${response.isSuccessful}")
            Log.d("RESPONSE VM:::", "${response.body()?.string()}")
            heartWriteRespone.value = response.isSuccessful;
        }
    }

    // 삼박수 불러오기
    fun getHeartRateList(page:Int = 0, size:Int = 7) { // swagger의 기본 값
        viewModelScope.launch(exceptionHandler) {
            val response: Response<ResponseBody> = repository.getHeartRateList(page, size);
            val myList: MutableList<HeartRead> = mutableListOf();

            Log.d(HLOG, "${response.toString()}")
            //Log.d(HLOG, "${response.body()?.string()}")

            if(response.isSuccessful){
                val jsonString = response.body()?.string()
                val jsonObject = JSONObject(jsonString)
                val dataArray = jsonObject.getJSONObject("data").getJSONArray("content")

                // 로깅용
                Log.d(HLOG, "${jsonObject}")

                for (i in 0 until dataArray.length()) {
                    val item = dataArray.getJSONObject(i)
                    val no = item.getInt("no")
                    val userNo = item.getInt("userNo")
                    val createTime = item.getString("createTime")
                    val count = item.getInt("count")

                    myList.add(HeartRead(no, userNo, createTime, count))
                    // 추출한 데이터 활용
                }
                heartListResponse.value = myList;
            }
            //Log.d("RESPONSE :::: ", "${myList}")
        }
    }


    // 코루틴 에러 핸들러 >> coroutine exception Handelr
    protected val exceptionHandler = CoroutineExceptionHandler{ i, exception ->
        Log.d("ERR ::::", "에러 발생.... ${exception.message}");
        Log.d("ERR ::::", "에러 발생.... ${exception.toString()}");
    }

    companion object {
        val heartRateList:MutableState<MutableList<HeartRead>> = mutableStateOf(mutableListOf());
        const val HLOG = "HEART_RATE_VIEW::::"
    }

}