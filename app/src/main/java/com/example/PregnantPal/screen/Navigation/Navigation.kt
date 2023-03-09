package com.example.pregnantpal.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pregnantpal.screen.Login.SignUpScreen
import com.example.pregnantpal.screen.Login.loginScreen
import com.example.pregnantpal.screen.Navigation.Screens
import com.example.pregnantpal.screen.Login.loginViewModel

@Composable
fun Navigation(
                navController: NavHostController = rememberNavController(),
                loginViewModel: loginViewModel
){

    NavHost(
        navController = navController, 
        startDestination = Screens.SignInScreen.name
    ){

        composable(route = Screens.SignInScreen.name){
            loginScreen(onNavToHomePage = {
                navController.navigate(Screens.MainScreen.name){
                    launchSingleTop = true
                    popUpTo(route = Screens.SignInScreen.name){
                        inclusive = true
                    }
                }
            },

                loginViewModel = loginViewModel

            ) {
                    navController.navigate(Screens.SignUpScreen.name){
                        launchSingleTop = true
                        popUpTo(Screens.SignInScreen.name){
                            inclusive = true
                        }
                    }
            }
        }

        composable(route = Screens.SignUpScreen.name){
            SignUpScreen(onNavToHomePage = {
                navController.navigate(Screens.MainScreen.name){
                    launchSingleTop = true
                    popUpTo(Screens.SignUpScreen.name){
                        inclusive = true
                    }
                }
            },

                loginViewModel = loginViewModel

                ) {
                navController.navigate(Screens.SignInScreen.name)
            }
        }

        composable(route = Screens.MainScreen.name){
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

