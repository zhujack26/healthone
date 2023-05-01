package com.secui.healthone.data.Sleep

data class SleepTime(val hour: Int, val minute: Int) {
    fun timetoFloat(): Float = hour + minute / 60f
}