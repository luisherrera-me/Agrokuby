package com.kubyapp.agrokuby.presentation.home_screen.components.ChartsData_Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kubyapp.agrokuby.navigation.Screens
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChartScreen(
    navController: NavHostController
){
    var isPressed by remember { mutableStateOf(false) }
    Card(
        onClick = {
            isPressed = true
        },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .height(90.dp)
            .padding(horizontal = 10.dp, vertical = 0.dp)
            .scale(if (isPressed) 0.996f else 1f)//Escala
            .alpha(if (isPressed) 0.98f else 1f)//Opacidad
            .clickable { Screens.ChartScreen.route},
        elevation = if (isPressed) 0.dp else 5.dp, //Modificación de la elevación
        backgroundColor = Color.White,
        shape = RoundedCornerShape(20.dp)
    ){

    }
    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(400)
            isPressed = false
        }
    }
}
