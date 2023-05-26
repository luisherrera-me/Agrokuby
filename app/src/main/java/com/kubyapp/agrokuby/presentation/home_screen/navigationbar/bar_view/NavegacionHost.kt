package com.kubyapp.agrokuby.presentation.home_screen.navigationbar.bar_view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.kubyapp.agrokuby.presentation.home_screen.navigationbar.Items_menu
import com.kubyapp.agrokuby.presentation.home_screen.navigationbar.bar_view.psychiatry_screen.Psychiatry
import com.kubyapp.agrokuby.presentation.home_screen.navigationbar.bar_view.starting_screen.startingScreen

@Composable
fun NavegacionHost(navController: NavController){
    NavHost(navController = navController as NavHostController,
        startDestination = Items_menu.Pantallas1.ruta
        ){
        composable(Items_menu.Pantallas1.ruta){
            settings()
        }
        composable(Items_menu.Pantallas2.ruta){
            startingScreen(navController = navController)
        }
        composable(Items_menu.Pantallas3.ruta){
            Psychiatry()
        }

    }
}