package com.kubyapp.agrokuby.presentation.home_screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kubyapp.agrokuby.R
import com.kubyapp.agrokuby.presentation.home_screen.components.MositureDataHolder
import com.kubyapp.agrokuby.presentation.home_screen.components.StatusRobot
import com.kubyapp.agrokuby.presentation.home_screen.components.TempDataHolder
import com.kubyapp.agrokuby.presentation.home_screen.navigationbar.navigation_bar
import com.kubyapp.agrokuby.presentation.lightness_screen.LightnessDataHolder
import com.kubyapp.agrokuby.presentation.lightness_screen.LightnessViewModel
import com.kubyapp.agrokuby.presentation.temperature_screen.TemperatureViewModel
import com.kubyapp.agrokuby.ui.theme.backgroundColor


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    temperatureViewModel: TemperatureViewModel = hiltViewModel(),
    lightnessViewModel: LightnessViewModel = hiltViewModel()
) {
    val lightness by lightnessViewModel.getLightness.collectAsState()
    val batterry by viewModel.getRobotStatus.collectAsState()
    val soil by temperatureViewModel.getTemperatureState.collectAsState()
    Log.d("TAG", "HomeScreen: ${lightness.lightNess}")
    Log.d("TAG", "HomeScreen: ${soil.temperature}")
    Log.d("TAG", "HomeScreen: ${batterry.BatterryRobot}")
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        backgroundColor = Color.Transparent,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal =0.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            IconButton(onClick = { /* action */ }) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(Color.Transparent, CircleShape)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_menu),
                                        contentDescription = "Logo",
                                        //contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(35.dp)
                                            .aspectRatio(1f)
                                            .align(Alignment.Center)
                                        //.fillMaxSize()
                                        //.clip(CircleShape)
                                    )

                                }
                            }
                        }
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.lg_agrokuby),
                                contentDescription = "Logo",
                                modifier = Modifier.size(100.dp)
                            )
                            Text(text = "")
                        }
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { /* acción */ }) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(Color.Transparent, CircleShape)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.fotoperfil),
                                        contentDescription = "Logo",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxSize()
                                    )
                                }
                            }
                        }
                    }
                },
                backgroundColor = Color.White,
                elevation = 0.dp,
                //modifier = Modifier.height(56.dp),
            )
        }
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    val lastIndex = batterry.BatterryRobot?.lastOrNull()
                    lastIndex?.let { StatusRobot(battery = it) }
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
                }

            }
        }
        navigation_bar()
    }
}
