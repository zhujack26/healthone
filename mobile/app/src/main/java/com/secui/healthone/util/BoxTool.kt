package com.secui.healthone.util

class BoxTool {
    companion object {
        fun getDisplayString(value:Int):String{
            return if(value < 0) "측정 오류" else value.toString()
        }
        fun getBpmDisplayString(value:Int):String{
            return if(value < 0) "측정 오류" else if(value==0) "-" else value.toString()
        }
        fun getSleepDisplayString(value:Int):String{

            val time = value/60;
            val hour = time/60;
            val min = time%60;

            if(hour>0 && min > 0){
                return "${hour}시간 ${min}분"
            }else if(hour > 0 && min == 0){
                return "${hour}시간"
            }else if(hour == 0 && min >= 0){
                return "${min} 분"
            }else {
              return "측정 오류"
            }
        }
    }
}