package com.example.pregnantpal.screen.Navigation

enum class Screens {
    SignUpScreen,
    SignInScreen,
    MainScreen,
    SettingsScreen,
    PregnantPalScreen,
    AdminScreen;
    companion object{
        fun fromRoute(route: String?): Screens = when (route?.substringBefore("/")){
            SignUpScreen.name -> SignUpScreen
            SignInScreen.name -> SignInScreen
            MainScreen.name -> MainScreen
            SettingsScreen.name -> SettingsScreen
            PregnantPalScreen.name -> PregnantPalScreen
            AdminScreen.name -> AdminScreen
            null -> MainScreen
            else -> throw java.lang.IllegalArgumentException("Route $route is not recognized")
        }
    }
}