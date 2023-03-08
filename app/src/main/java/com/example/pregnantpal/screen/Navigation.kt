package com.example.pregnantpal.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigaton(){
    val navController = rememberNavController()
    NavHost(
        navController = navController, 
        startDestination = Screens.MainScreen.name
    ){
        composable(Screens.MainScreen.name){
            MainScreen(navController = navController)
        }

        composable(
            route = Screens.PregnantPalScreen.name + "/{pregnantPal_screen}",
            arguments = listOf(navArgument(name="pregnantPal_screen"){type = NavType.StringType})
        ){backStackEntry ->
            PregnantPalScreen(navController = navController, backStackEntry.arguments?.getString("pregnantPal_screen"))
        }
    }
}

