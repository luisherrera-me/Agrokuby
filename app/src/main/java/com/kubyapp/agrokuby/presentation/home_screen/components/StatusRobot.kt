package com.kubyapp.agrokuby.presentation.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kubyapp.agrokuby.R
import com.kubyapp.agrokuby.data.model.RobotStatus.BatterryRobot
import com.kubyapp.agrokuby.ui.theme.BatteryFull
import com.kubyapp.agrokuby.ui.theme.TempPulple
import com.kubyapp.agrokuby.ui.theme.lightBlue


@Composable
fun StatusRobot(
    battery: BatterryRobot
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 20.dp)
            .padding(vertical = 6.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colors.background),

        elevation = 4.dp
    ) {
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
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .size(40.dp)
                            .size(width = 70.dp, height = 25.dp)
                            .background(BatteryFull, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            imageVector = when {
                                battery.BatteryCharging -> ImageVector.vectorResource(id = R.drawable.ic_battery_charging)
                                battery.BatteryCapacity >= 90 -> ImageVector.vectorResource(id = R.drawable.ic_battery_full)
                                battery.BatteryCapacity >= 80 -> ImageVector.vectorResource(id = R.drawable.ic_battery_80)
                                battery.BatteryCapacity >= 60 -> ImageVector.vectorResource(id = R.drawable.ic_battery_60)
                                battery.BatteryCapacity >= 40 -> ImageVector.vectorResource(id = R.drawable.ic_battery_40)
                                battery.BatteryCapacity >= 30 -> ImageVector.vectorResource(id = R.drawable.ic_battery_30)
                                battery.BatteryCapacity >= 20 -> ImageVector.vectorResource(id = R.drawable.ic_battery_20)
                                battery.BatteryCapacity >= 10 -> ImageVector.vectorResource(id = R.drawable.ic_battery_10)
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
    }


}

