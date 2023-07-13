package com.kubyapp.agrokuby.presentation.home_screen.navigationbar

import androidx.compose.ui.graphics.Color
import com.kubyapp.agrokuby.R
import com.kubyapp.agrokuby.ui.theme.AmarilloSuave
import com.kubyapp.agrokuby.ui.theme.AzulCielo
import com.kubyapp.agrokuby.ui.theme.psycriatryColor

sealed class Items_menu (
    val icon: Int,
    val title: String,
    val ruta: String,
    val iconColor: Color
        ){
    object Pantallas1: Items_menu(
        R.drawable.ic_settings,
        "Setting",
        "pantalla1",
        AzulCielo)

    object Pantallas3: Items_menu(
        R.drawable.ic_navigation,
        "Plant", "pantalla2",
         psycriatryColor)

    object Pantallas2: Items_menu(
        R.drawable.ic_house,
        "Home", "pantalla3",
        AmarilloSuave)

    object userInformation: Items_menu(
        R.drawable.ic_user,
        "UserInformation", "pantalla4",
        AmarilloSuave)

    object Splash: Items_menu(
        R.drawable.ic_user,
        "Splash", "pantalla5",
        AmarilloSuave)
}
