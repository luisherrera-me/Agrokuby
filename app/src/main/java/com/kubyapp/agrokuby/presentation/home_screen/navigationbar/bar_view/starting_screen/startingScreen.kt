package com.kubyapp.agrokuby.presentation.home_screen.navigationbar.bar_view.starting_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kubyapp.agrokuby.presentation.home_screen.HomeViewModel
import com.kubyapp.agrokuby.presentation.home_screen.components.BarometricData.BarometricDataHolder
import com.kubyapp.agrokuby.presentation.home_screen.components.BarometricData.BarometricViewModel
import com.kubyapp.agrokuby.presentation.home_screen.components.MositureDataHolder
import com.kubyapp.agrokuby.presentation.home_screen.components.Robot_screen.SensSwitch
import com.kubyapp.agrokuby.presentation.home_screen.components.StatusRobot
import com.kubyapp.agrokuby.presentation.home_screen.components.TempDataHolder
import com.kubyapp.agrokuby.presentation.home_screen.components.lightness_screen.LightnessDataHolder
import com.kubyapp.agrokuby.presentation.home_screen.components.lightness_screen.LightnessViewModel
import com.kubyapp.agrokuby.presentation.home_screen.components.temperature_screen.TemperatureViewModel
import com.kubyapp.agrokuby.presentation.home_screen.components.user_screen.UserViewModel
import com.kubyapp.domain.repository.StatusRobot


@Composable
fun currentRouter(navController: NavHostController):String?{
    val entrada by navController.currentBackStackEntryAsState()
    return entrada?.destination?.route
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun startingScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    temperatureViewModel: TemperatureViewModel = hiltViewModel(),
    lightnessViewModel: LightnessViewModel = hiltViewModel(),
    userData: UserViewModel = hiltViewModel(),
    barometricViewModel: BarometricViewModel = hiltViewModel(),
    navController: NavHostController
){
    // Crear estados para almacenar los datos obtenidos de Firebase
    val lightnessState by lightnessViewModel.getLightness.collectAsState()
    val batteryState by viewModel.getRobotStatus.collectAsState()
    val widgetRobotState by viewModel.getWidgetRobot.collectAsState()
    val soilState by temperatureViewModel.getTemperatureState.collectAsState()
    val userState by userData.UserStatus.collectAsState()
    val barometricState by barometricViewModel.getBarometricState.collectAsState()
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                val lastIndex = batteryState.BatterryRobot?.lastOrNull()
                lastIndex?.let { StatusRobot(battery = it, navController) }
            }
            item {
                val lastIndex = widgetRobotState.WidgetRobot?.lastOrNull()
                lastIndex?.let { SensSwitch(Widget = it) }
            }
            item {
                val lastIndex = barometricState.Barometric?.lastOrNull()
                lastIndex?.let { BarometricDataHolder(barometric = it) }
            }
            item {
                val lastIndex = lightnessState.lightNess?.lastOrNull()
                lastIndex?.let {
                    LightnessDataHolder(lightNess = it, navController = navController)
                }
            }
            item {
                val lastIndex = soilState.temperature?.lastOrNull()
                lastIndex?.let { TempDataHolder(soil = it) }
            }
            item {
                val lastIndex = soilState.temperature?.lastOrNull()
                lastIndex?.let { MositureDataHolder(soil = it) }
                Spacer(modifier = Modifier.height(110.dp))
            }
        }
    }


}