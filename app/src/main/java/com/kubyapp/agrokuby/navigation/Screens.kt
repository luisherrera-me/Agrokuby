package com.kubyapp.agrokuby.navigation

sealed class Screens(val route: String) {
    object SignInScreen : Screens(route = "SignIn_Screen")
    object SignUpScreen : Screens(route = "SignUp_Screen")
    object HomeScreen : Screens(route = "Home_Screen")
    object SplashScreen : Screens(route = "Splash_Screen")
    object ChartScreen : Screens(route = "Chart_Screen")
    object InfoRobotScreen : Screens(route = "InfoRobot_Scree")

}