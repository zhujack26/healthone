package com.secui.healthone.util

sealed class PageRoutes(val route:String){

    object OverView: PageRoutes("OverViewPage")
    object MealPlan: PageRoutes("MealPlanPage")
    object Login: PageRoutes("LoginPage")
    object HeartRate: PageRoutes("HeartRatePage")
    object Challenge: PageRoutes("ChallengePage")
    object Alert: PageRoutes("AlertPage")
    object HeartMeasure: PageRoutes("HeartMeasurePage");
    object MealInput: PageRoutes("MealInputPage")
    object ExerciseInput: PageRoutes("ExerciseInputPage")
    object Sleep: PageRoutes("SleepPage")
    object DataCollectFirst : PageRoutes("DataCollectFirst")
    object DataCollectSecond : PageRoutes("DataCollectSecond")
    object My: PageRoutes("MyPage")
    object Walking: PageRoutes("WalkingPage")
    object PopularDetail: PageRoutes("PopularDetailPage")
    object Setting: PageRoutes("SettingPage")
    object Guide: PageRoutes("GuidePage")
    object HealthStatus: PageRoutes("HealthStatusPage")
    object HealthStatusInput: PageRoutes("HealthInputPage")
    object HealthHelp: PageRoutes("HealthHelpPage")
}
