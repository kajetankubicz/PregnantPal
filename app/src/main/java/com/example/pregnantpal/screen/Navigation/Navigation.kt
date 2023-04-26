package com.example.PregnantPal.screen.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.PregnantPal.screen.AdminScreen.AdminScreen
import com.example.PregnantPal.screen.login.SignUpScreen
import com.example.PregnantPal.screen.login.loginScreen
import com.example.PregnantPal.screen.login.loginViewModel
import com.example.PregnantPal.screen.HomeScreen.MainScreen
import com.example.PregnantPal.screen.DetailScreen.PregnantPalScreen
import com.example.PregnantPal.screen.SettingsScreen.SettingsScreen

//Function that creates navigation graph
@Composable
fun Navigation(
                navController: NavHostController = rememberNavController(),
                loginViewModel: loginViewModel
){

    //NavHost function is ued to define the start destination and the routes for each screen of the app
    //NavHost contains multiple composable functions that define each screen
    //The route parameter in each composable functions defines the name of the route for that screen
    NavHost(
        navController = navController,
        startDestination = Screens.SignInScreen.name
    ){

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

        composable(route = Screens.AdminScreen.name){
            AdminScreen(navController = navController)
        }

        composable(route = Screens.SettingsScreen.name){
            SettingsScreen(navController = navController)
        }

        composable(route = Screens.PregnantPalScreen.name){
            PregnantPalScreen(navController = navController)
        }

        composable(route = Screens.SignInScreen.name){
            loginScreen(
                onNavToHomePage = {
                    navController.navigate(Screens.MainScreen.name){
                        launchSingleTop = true
                        popUpTo(route = Screens.SignInScreen.name){
                            inclusive = true
                        }
                    }
                },
                onNavToSignUpPage = {
                    navController.navigate(Screens.SignUpScreen.name){
                        launchSingleTop = true
                        popUpTo(Screens.SignInScreen.name){
                            inclusive = true
                        }
                    }
                },
                onNavToAdminPage = {
                    navController.navigate(Screens.AdminScreen.name){
                        launchSingleTop = true
                        popUpTo(Screens.SignInScreen.name){
                            inclusive = true
                        }
                    }
                },
                loginViewModel = loginViewModel
            )
        }

    }
}

