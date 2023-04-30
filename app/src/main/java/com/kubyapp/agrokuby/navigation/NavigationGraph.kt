package com.kubyapp.agrokuby.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kubyapp.agrokuby.presentation.home_screen.HomeScreen
import com.kubyapp.agrokuby.presentation.login_screen.SignInScreen
import com.kubyapp.agrokuby.presentation.signup_screen.SignUpScreen
import com.kubyapp.agrokuby.presentation.Splash_Screen.AnimatedSplashScreen

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(route = Screens.SignInScreen.route) {
            SignInScreen(navController)
        }
        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(navController)
        }
        composable(route = Screens.HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(route = Screens.SplashScreen.route){
            AnimatedSplashScreen(navController = navController)
        }
    }

}
