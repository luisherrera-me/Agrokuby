package com.kubyapp.agrokuby.navigation

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kubyapp.agrokuby.presentation.Splash_Screen.AnimatedSplashScreen
import com.kubyapp.agrokuby.presentation.home_screen.HomeScreen
import com.kubyapp.agrokuby.presentation.home_screen.components.ChartsData_Screen.ChartScreen
import com.kubyapp.agrokuby.presentation.home_screen.components.Robot_screen.InfoRobotView
import com.kubyapp.agrokuby.presentation.login_screen.SignInScreen
import com.kubyapp.agrokuby.presentation.signup_screen.SignUpScreen
import kotlinx.coroutines.launch

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
        composable(route = Screens.HomeScreen.route) {

            val scope = rememberCoroutineScope()
            val scaffoldState = rememberScaffoldState()
            HomeScreen(navController = navController)
        }
        composable(route = Screens.SplashScreen.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = Screens.ChartScreen.route) {
            ChartScreen(navController)
        }
        composable(route = Screens.InfoRobotScreen.route) {
            InfoRobotView(navController)
        }

    }

}



