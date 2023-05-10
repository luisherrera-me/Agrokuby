package com.kubyapp.agrokuby.presentation.home_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kubyapp.agrokuby.R
import com.kubyapp.agrokuby.navigation.Screens
import com.kubyapp.agrokuby.presentation.home_screen.components.BarometricData.BarometricDataHolder
import com.kubyapp.agrokuby.presentation.home_screen.components.BarometricData.BarometricViewModel
import com.kubyapp.agrokuby.presentation.home_screen.components.MositureDataHolder
import com.kubyapp.agrokuby.presentation.home_screen.components.StatusRobot
import com.kubyapp.agrokuby.presentation.home_screen.components.TempDataHolder
import com.kubyapp.agrokuby.presentation.home_screen.components.user_screen.DropDown
import com.kubyapp.agrokuby.presentation.home_screen.components.user_screen.UserScreen
import com.kubyapp.agrokuby.presentation.home_screen.components.user_screen.UserViewModel
import com.kubyapp.agrokuby.presentation.home_screen.components.lightness_screen.LightnessDataHolder
import com.kubyapp.agrokuby.presentation.home_screen.components.lightness_screen.LightnessViewModel
import com.kubyapp.agrokuby.presentation.home_screen.components.temperature_screen.TemperatureViewModel
import com.kubyapp.agrokuby.presentation.home_screen.components.user_screen.ProfilePicture
import com.kubyapp.agrokuby.presentation.home_screen.navigationbar.CustomBottomNavigation
import com.kubyapp.agrokuby.presentation.home_screen.navigationbar.Items_menu
import com.kubyapp.agrokuby.presentation.home_screen.navigationbar.bar_view.NavegacionHost
import com.kubyapp.agrokuby.presentation.home_screen.navigationbar.navigation_bar

import com.kubyapp.agrokuby.ui.theme.backgroundColor
import com.kubyapp.agrokuby.ui.theme.gray300

@Composable
fun currentRouter(navController: NavHostController):String?{
    val entrada by navController.currentBackStackEntryAsState()
    return entrada?.destination?.route
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    temperatureViewModel: TemperatureViewModel = hiltViewModel(),
    lightnessViewModel: LightnessViewModel = hiltViewModel(),
    userData: UserViewModel = hiltViewModel(),
    barometricViewModel: BarometricViewModel = hiltViewModel(),
    navController: NavHostController

) {

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val navigacion_item = listOf(
        Items_menu.Pantallas1,
        Items_menu.Pantallas2,
        Items_menu.Pantallas3

    )


    val lightness by lightnessViewModel.getLightness.collectAsState()
    val batterry by viewModel.getRobotStatus.collectAsState()
    val soil by temperatureViewModel.getTemperatureState.collectAsState()
    val user by userData.UserStatus.collectAsState()
    val barometric by barometricViewModel.getBarometricState.collectAsState()
    var expanded by remember { mutableStateOf(false) }

    Log.d("TAG", "HomeScreen: ${barometric.Barometric}")
    Log.d("TAG", "HomeScreen: ${lightness.lightNess}")
    Log.d("TAG", "HomeScreen: ${soil.temperature}")
    Log.d("TAG", "HomeScreen: ${user.User}")
    Log.d("TAG", "HomeScreen: ${batterry.BatterryRobot}")
    //val scaffoldState = rememberScaffoldState()

    Scaffold(
        backgroundColor = Color.Transparent,
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                    .padding(start = 0.dp, end = 0.dp),
                backgroundColor = Color.White,
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = "Navegar hacia atrás",
                            modifier = Modifier.size(36.dp)
                        )
                    }
                },
                actions = {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.lg_agrokuby),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(150.dp)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        user.User?.let {
                            ProfilePicture(onExpandChange = {
                                expanded = !expanded
                            }, userInfo = it)
                        }
                        //dropdownmenu
                        user.User?.let {
                            DropDown(
                                userInfo = it, onClick = {
                                    viewModel.signOut()
                                    navController.popBackStack()
                                    navController.navigate(Screens.SignInScreen.route)
                                },
                                expanded = expanded,
                                onDismissClick = {
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            )
        },

    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                /*
                item {
                    user.User?.let { it1 -> UserScreen(userInfo = it1) }
                }
                */
                item {
                    val lastIndex = batterry.BatterryRobot?.lastOrNull()
                    lastIndex?.let { StatusRobot(battery = it) }
                }
                item {
                    val lastIndex = barometric.Barometric?.lastOrNull()
                    lastIndex?.let { BarometricDataHolder(barometric = it) }
                }
                item {
                    val lastIndex = lightness.lightNess?.lastOrNull()
                    lastIndex?.let {
                        LightnessDataHolder(lightNess = it, navController = navController)
                    }
                }
                item {
                    val lastIndex = soil.temperature?.lastOrNull()
                    lastIndex?.let { TempDataHolder(soil = it) }
                }
                item {
                    val lastIndex = soil.temperature?.lastOrNull()
                    lastIndex?.let { MositureDataHolder(soil = it) }
                    Spacer(modifier = Modifier.height(110.dp))
                }
            }
        }

        Scaffold (
            scaffoldState = scaffoldState,
            bottomBar = { CustomBottomNavigation(navController, navigacion_item)}
                ){
            NavegacionHost(navController)
        }
    }
}
