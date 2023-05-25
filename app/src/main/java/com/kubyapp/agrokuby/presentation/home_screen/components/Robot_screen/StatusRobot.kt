package com.kubyapp.agrokuby.presentation.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kubyapp.agrokuby.R
import com.kubyapp.agrokuby.data.model.RobotStatus.BatterryRobot
import com.kubyapp.agrokuby.navigation.Screens
import com.kubyapp.agrokuby.presentation.home_screen.navigationbar.CustomBottomNavigation
import com.kubyapp.agrokuby.presentation.home_screen.navigationbar.bar_view.NavegacionHost
import com.kubyapp.agrokuby.ui.theme.BatteryFull
import com.kubyapp.agrokuby.ui.theme.ORANGE_LIGHT
import com.kubyapp.agrokuby.ui.theme.RegularFont
import com.kubyapp.agrokuby.ui.theme.TempPulple
import com.kubyapp.agrokuby.ui.theme.lightBlue
import com.kubyapp.agrokuby.ui.theme.lightRed
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StatusRobot(
    battery: BatterryRobot,
    navController: NavHostController
) {
    var isPressed by remember { mutableStateOf(false) }

    Text(
        modifier = Modifier.padding(top = 10.dp, start = 35.dp, end = 35.dp ),
        text = "information for the robot",
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = Color.Gray,
        fontFamily = RegularFont
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .height(120.dp)
            .clickable {}
            .padding(horizontal = 10.dp, vertical = 0.dp)
            .scale(if (isPressed) 0.996f else 1f)//Escala
            .alpha(if (isPressed) 0.98f else 1f),//Opacidad
        elevation = if (isPressed) 0.dp else 5.dp, //Modificación de la elevación
        backgroundColor = Color.White,
        onClick = {isPressed = true},
        shape = RoundedCornerShape(20.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            )  {
                IconButton(onClick = { /* acción */ }) {
                    val batteryPercentage  = battery.BatteryCapacity
                    val batteryColor = when {

                        battery.BatteryCharging -> ORANGE_LIGHT
                        battery.BatteryCharging -> BatteryFull
                        batteryPercentage >= 90 -> BatteryFull
                        batteryPercentage >= 80 -> BatteryFull
                        batteryPercentage >= 60 -> BatteryFull
                        batteryPercentage >= 40 -> ORANGE_LIGHT
                        batteryPercentage >= 30 -> ORANGE_LIGHT
                        batteryPercentage >= 20 -> lightRed
                        batteryPercentage >= 10 -> lightRed
                        else -> lightRed
                    }

                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .size(40.dp)
                            .size(width = 70.dp, height = 25.dp)
                            .background(batteryColor, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            imageVector = when {
                                battery.BatteryCharging -> ImageVector.vectorResource(id = R.drawable.ic_bolt)
                                batteryPercentage >= 90 -> ImageVector.vectorResource(id = R.drawable.ic_battery_full)
                                batteryPercentage >= 80 -> ImageVector.vectorResource(id = R.drawable.ic_battery_80)
                                batteryPercentage >= 60 -> ImageVector.vectorResource(id = R.drawable.ic_battery_60)
                                batteryPercentage >= 40 -> ImageVector.vectorResource(id = R.drawable.ic_battery_40)
                                batteryPercentage >= 30 -> ImageVector.vectorResource(id = R.drawable.ic_battery_30)
                                batteryPercentage >= 20 -> ImageVector.vectorResource(id = R.drawable.ic_battery_20)
                                batteryPercentage >= 10 -> ImageVector.vectorResource(id = R.drawable.ic_battery_10)
                                else -> ImageVector.vectorResource(id = R.drawable.ic_battery_empty)
                            },
                            contentDescription = "Battery Icon",
                            modifier = Modifier.size(35.dp)
                        )
                    }
            }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Batterry",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        fontWeight = FontWeight.Medium,
                        text = "${battery.BatteryTime} minutes",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "average",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(5.dp))
                    IconButton(onClick = { /* acción */ }) {
                        Box(
                            modifier = Modifier
                                .width(72.dp)
                                .size(width = 70.dp, height = 25.dp)
                                .background(lightBlue, CircleShape)
                                .align(Alignment.Start),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${battery.BatteryCapacity} %",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }

        }
        LaunchedEffect(isPressed) {
            if (isPressed) {
                delay(400)
                isPressed = false
                navController.navigate(Screens.ChartScreen.route)
            }
        }
    }

}

