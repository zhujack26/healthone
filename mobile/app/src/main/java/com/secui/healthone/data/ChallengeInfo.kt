package com.secui.healthone.data

data class ChallengeInfo (
    val no:Int,
    val name:String,
    val introduce:String,
    val totalWorkCount:Int,
    val totalPeriod:Int,
    val level:String,
    val avgWorkTime:Int,
    val sportEquipmentCheck:Boolean,
    val equipment:String?,
    val programType:String,
    val recommendWeek:String,
    val youtubeLink:String,
    val thumbnailLink:String,
    val participants:Int,
    val hits:Int,
    val participantsCheck:Boolean
)

