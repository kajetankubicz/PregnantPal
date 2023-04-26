package com.example.PregnantPal.screen.Navigation

//Enum class represents the different screens in PregnantPal app
enum class Screens {
    SignUpScreen,
    SignInScreen,
    MainScreen,
    SettingsScreen,
    PregnantPalScreen,
    AdminScreen;
    //Companion object defines a function 'fromRoute'  which takes a String parameter that represent a route and return a Screen object
    companion object{
        //When expression matches the route parameter and if a match is found then the corresponding Screen object is returned
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