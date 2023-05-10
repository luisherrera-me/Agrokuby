package com.kubyapp.agrokuby.presentation.home_screen.navigationbar.bar_view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.kubyapp.agrokuby.presentation.home_screen.navigationbar.Items_menu

@Composable
fun NavegacionHost(navController: NavController){
    NavHost(navController = navController as NavHostController,
        startDestination = Items_menu.Pantallas1.ruta
        ){
        composable(Items_menu.Pantallas1.ruta){
            settings()
        }
        composable(Items_menu.Pantallas2.ruta){
            home()
        }
        composable(Items_menu.Pantallas3.ruta){
            Psychiatry()
        }

    }
}