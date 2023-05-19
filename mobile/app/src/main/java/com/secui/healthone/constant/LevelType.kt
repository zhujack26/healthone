package com.secui.healthone.constant

sealed class LevelType(val type:String) {
    object Low : LevelType("쉬움")
    object Normal : LevelType("보통")
    object High : LevelType("어려움")
    object Default: LevelType("-")
}