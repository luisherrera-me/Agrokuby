package com.kubyapp.agrokuby.presentation.home_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.kubyapp.agrokuby.R
import com.kubyapp.agrokuby.presentation.home_screen.components.MositureDataHolder
import com.kubyapp.agrokuby.presentation.lightness_screen.LightnessDataHolder
import com.kubyapp.agrokuby.presentation.home_screen.components.TempDataHolder
import com.kubyapp.agrokuby.presentation.lightness_screen.LightnessViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kubyapp.agrokuby.presentation.home_screen.components.StatusRobot
import com.kubyapp.agrokuby.ui.theme.backgroundColor



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    lightnessViewModel: LightnessViewModel = hiltViewModel()
) {
    val lightness by lightnessViewModel.getLightness.collectAsState()
    val batterry by viewModel.getRobotStatus.collectAsState()
    val soil by viewModel.homeState.collectAsState()
    Log.d("TAG", "HomeScreen: ${lightness.lightNess}")
    Log.d("TAG", "HomeScreen: ${soil.soil}")
    Log.d("TAG", "HomeScreen: ${batterry.BatterryRobot}")
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        backgroundColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.lg_agrokuby_login),
                        contentDescription = "Logo",
                        modifier = Modifier.size(100.dp)
                    )
                    Text(text = "")
                }
            },
                backgroundColor = Color.White,
                elevation = 0.dp,
                modifier = Modifier.height(56.dp),

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
                    val lastIndex = soil.soil?.lastOrNull()
                    lastIndex?.let { TempDataHolder(soil = it ) }
                }
                item {
                    val lastIndex = soil.soil?.lastOrNull()
                    lastIndex?.let { MositureDataHolder(soil = it ) }
                }
            }
        }
    }
}
