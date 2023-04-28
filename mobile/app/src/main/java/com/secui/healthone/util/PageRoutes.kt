package com.secui.healthone.util

sealed class PageRoutes(val route:String){

    object OverView: PageRoutes("OverViewPage")
    object MealPlan: PageRoutes("MealPlanPage")
    object Login: PageRoutes("LoginPage")
    object StressIndex: PageRoutes("StressIndexPage")
    object HeartRate: PageRoutes("HeartRatePage")
    object Challenge: PageRoutes("ChallengePage")
    object Alert: PageRoutes("AlertPage")
    object StressBreath: PageRoutes("StressBreathPage")
    object StressActivity: PageRoutes("StressActivityPage")
    object HeartMeasure: PageRoutes("HeartMeasurePage");
    object MealInput: PageRoutes("MealInputPage")
    object ExerciseInput: PageRoutes("ExerciseInputPage")
    object My: PageRoutes("MyPage")
    object Walking: PageRoutes("WalkingPage")
    object PopularDetail: PageRoutes("PopularDetailPage")
    object Setting: PageRoutes("SettingPage")
}
