package com.secui.healthone.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secui.healthone.constant.LevelType
import com.secui.healthone.data.ChallengeInfo
import com.secui.healthone.repository.ChallengeRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.json.JSONObject

class ChallenegeViewModel(val repository: ChallengeRepository):ViewModel() {

    val challengeList: MutableLiveData<MutableList<ChallengeInfo>> = MutableLiveData()

    fun getChallengeList(){
        viewModelScope.launch(exceptionHandler) {
            val response = repository.getChallengeList();
            val myList: MutableList<ChallengeInfo> = mutableListOf();

            Log.d(CLOG, "${response.toString()}")
            Log.d(CLOG, "${response.body()?.string()}")

            if(!response.isSuccessful) throw Exception();

            val jsonString = response.body()?.string()
            val jsonObject = JSONObject(jsonString)
            val dataArray = jsonObject.getJSONObject("data").getJSONArray("popularChallengeList")

            for (i in 0 until dataArray.length()) {
                val item = dataArray.getJSONObject(i)

                val challengeInfo = ChallengeInfo(
                    no = item.getInt("no"),
                    name = item.getString("name"),
                    introduce = item.getString("introduce"),
                    totalWorkCount = item.getInt("totalWorkCount"),
                    totalPeriod = item.getInt("totalPeriod"),
                    level = parseLevelString(item.getString("level")),
                    avgWorkTime = item.getInt("avgWorkTime"),
                    sportEquipmentCheck = item.getBoolean("sportEquipmentCheck"),
                    equipment = item.getString("equipment"),
                    programType = item.getString("programType"),
                    recommendWeek = item.getString("recommendWeek"),
                    youtubeLink = item.getString("youtubeLink"),
                    thumbnailLink = item.getString("thumbnailLink"),
                    participants = item.getInt("participants"),
                    hits = item.getInt("hits"),
                    participantsCheck = item.getBoolean("participantsCheck")
                )

                myList.add(challengeInfo)
                // 추출한 데이터 활용
            }

            challengeList.value = myList;
            // Log.d("RESPONSE :::: ", "${myList}")
        }
    }


    // 코루틴 에러 핸들러 >> coroutine exception Handelr
    protected val exceptionHandler = CoroutineExceptionHandler{ i, exception ->
        Log.d("ERR ::::", "에러 발생.... ${exception.message}");
        Log.d("ERR ::::", "에러 발생.... ${exception.toString()}");
    }

    fun parseLevelString(levelEnum:String):String{
        return when(levelEnum){
            "LOW" -> LevelType.Low.type
            "NORMAL" -> LevelType.Normal.type
            "HIGH" -> LevelType.High.type
            else -> LevelType.Default.type;
        }
    }

    companion object{
        val challengeList: MutableState<MutableList<ChallengeInfo>> = mutableStateOf(mutableListOf())
        val currentChallenge : MutableState<ChallengeInfo?> = mutableStateOf(null);
        const val CLOG = "CHALLENGE_VIEW::::"
    }


}