package com.example.pregnantpal.screen


enum class Screens {
    MainScreen,
    PregnantPalScreen;
    companion object{
        fun fromRoute(route: String?): Screens = when (route?.substringBefore("/")){
            MainScreen.name -> MainScreen
            PregnantPalScreen.name -> PregnantPalScreen
            null -> MainScreen
            else -> throw java.lang.IllegalArgumentException("Route $route is not recognized")
        }
    }
}