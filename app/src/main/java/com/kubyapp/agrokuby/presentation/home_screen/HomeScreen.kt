package com.kubyapp.agrokuby.presentation.home_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kubyapp.agrokuby.R
import com.kubyapp.agrokuby.data.model.sensors.Soil
import com.kubyapp.agrokuby.data.model.user.UserInfo
import com.kubyapp.agrokuby.navigation.Screens
import com.kubyapp.agrokuby.presentation.home_screen.components.BarometricData.BarometricDataHolder
import com.kubyapp.agrokuby.presentation.home_screen.components.BarometricData.BarometricViewModel
import com.kubyapp.agrokuby.presentation.home_screen.components.MositureDataHolder
import com.kubyapp.agrokuby.presentation.home_screen.components.StatusRobot
import com.kubyapp.agrokuby.presentation.home_screen.components.TempDataHolder
import com.kubyapp.agrokuby.presentation.home_screen.components.User_screen.UserScreen
import com.kubyapp.agrokuby.presentation.home_screen.components.User_screen.UserViewModel
import com.kubyapp.agrokuby.presentation.home_screen.navigationbar.navigation_bar
import com.kubyapp.agrokuby.presentation.home_screen.components.lightness_screen.LightnessDataHolder
import com.kubyapp.agrokuby.presentation.home_screen.components.lightness_screen.LightnessViewModel
import com.kubyapp.agrokuby.presentation.home_screen.components.temperature_screen.TemperatureViewModel
import com.kubyapp.agrokuby.ui.theme.RegularFont
import com.kubyapp.agrokuby.ui.theme.backgroundColor
import com.kubyapp.agrokuby.ui.theme.lightBlue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    temperatureViewModel: TemperatureViewModel = hiltViewModel(),
    lightnessViewModel: LightnessViewModel = hiltViewModel(),
    barometricViewModel: BarometricViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    navController: NavController
) {
    var isPressed by remember { mutableStateOf(false) }

    val lightness by lightnessViewModel.getLightness.collectAsState()
    val DataRobot by viewModel.getRobotStatus.collectAsState()
    val soil by temperatureViewModel.getTemperatureState.collectAsState()
    val barometric by barometricViewModel.getBarometricState.collectAsState()
    val UserInfo by userViewModel.UserStatus.collectAsState()
    var expanded by remember { mutableStateOf(false) }

    Log.d("TAG", "HomeScreen: ${barometric.Barometric}")
    Log.d("TAG", "HomeScreen: ${lightness.lightNess}")
    Log.d("TAG", "HomeScreen: ${soil.temperature}")
    Log.d("TAG", "HomeScreen: ${UserInfo.User}")
    Log.d("TAG", "HomeScreen: ${DataRobot.BatterryRobot}")
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
                        IconButton(onClick = { expanded = true }) {
                            Image(
                                painter = painterResource(id = R.drawable.user),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(35.dp)
                                    .clip(CircleShape)
                            )
                        }
                        Box(
                            contentAlignment = Alignment.Center,

                        ) {
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier
                                    .background(Color.White, RoundedCornerShape(8.dp))
                            ) {
                                Text(
                                    modifier = Modifier.padding(16.dp),
                                    text = "Hello Camila Diaz",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    color = Color.Gray,
                                    fontFamily = RegularFont
                                )
                                Divider(Modifier.padding(horizontal = 10.dp))
                                DropdownMenuItem(
                                    onClick = { /* acción */ }
                                ) {
                                    Icon(
                                        ImageVector.vectorResource(R.drawable.account_circle),
                                        contentDescription = "Perfil",
                                        modifier = Modifier
                                            .padding(end = 8.dp)
                                            .size(24.dp)
                                    )
                                    Text("Perfil")
                                }
                                DropdownMenuItem(
                                    onClick = { /* acción */ }
                                ) {
                                    Icon(
                                        ImageVector.vectorResource(R.drawable.help),
                                        contentDescription = "Ayuda",
                                        modifier = Modifier
                                            .padding(end = 8.dp)
                                            .size(24.dp)
                                    )
                                    Text("Ayuda")
                                }
                                Divider(Modifier.padding(horizontal = 10.dp))
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.signOut()
                                        navController.popBackStack()
                                        navController.navigate(Screens.SignInScreen.route)
                                    }
                                ) {
                                    Icon(
                                        ImageVector.vectorResource(R.drawable.logout),
                                        contentDescription = "Cerrar sesión",
                                        modifier = Modifier
                                            .padding(end = 8.dp)
                                            .size(24.dp)
                                    )
                                    Text("Cerrar sesión")
                                }
                            }

                        }
                    }
                }
            )
        },
        bottomBar = {
            navigation_bar()
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {

                item {
                    val lastIndex = UserInfo.User?.lastOrNull()
                    lastIndex?.let { UserScreen(userInfo = it) }
                }

                item {
                    val lastIndex = DataRobot.BatterryRobot?.lastOrNull()
                    lastIndex?.let { StatusRobot(battery = it) }
                }
                item {
                    val lastIndex = barometric.Barometric?.lastOrNull()
                    lastIndex?.let { BarometricDataHolder(barometric = it) }
                }
                item {
                    val lastIndex = lightness.lightNess?.lastOrNull()
                    lastIndex?.let { LightnessDataHolder(lightNess = it) }
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
    }
    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(400)
            isPressed = false
        }
    }
}


