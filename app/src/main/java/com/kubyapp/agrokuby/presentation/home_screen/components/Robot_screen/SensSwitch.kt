package com.kubyapp.agrokuby.presentation.home_screen.components.Robot_screen



import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.collection.LLRBNode
import com.kubyapp.agrokuby.R
import com.kubyapp.agrokuby.data.model.RobotStatus.WidgetRobot
import com.kubyapp.agrokuby.ui.theme.BatteryFull
import com.kubyapp.agrokuby.ui.theme.ORANGE_LIGHT
import com.kubyapp.agrokuby.ui.theme.gray100
import com.kubyapp.agrokuby.ui.theme.gray200
import com.kubyapp.agrokuby.ui.theme.lightBlue
import com.kubyapp.agrokuby.ui.theme.lightRed
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SensSwitch(
    Widget: WidgetRobot?,
    width: Dp = 55.dp,
    height: Dp = 30.dp,
    checkedTrackColor: Color = Color(0xFF35898F),
    uncheckedTrackColor: Color = Color(0xFF9B9B9B),
    uncheckedGapBetweenThumbAndTrackEdge: Dp = 4.dp,
    checkedGapBetweenThumbAndTrackEdge: Dp = 8.dp,
    borderWidth: Dp = 4.dp,
    cornerSize: Int = 50,
    iconInnerPadding: Dp = 4.dp,
    checkedThumbSize: Dp = 26.dp,
    uncheckedThumbSize: Dp = 19.dp
) {

    var isPressed by remember { mutableStateOf(false) }
    // this is to disable the ripple effect
    //esto es para deshabilitar el efecto dominó
    val interactionSource = remember {
        MutableInteractionSource()
    }



    Spacer(modifier = Modifier.width(26.dp))


    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .height(80.dp)
                    .width(200.dp)
                    .clickable {}
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .scale(if (isPressed) 0.996f else 1f)//Escala
                    .alpha(if (isPressed) 0.98f else 1f),//Opacidad
                elevation = if (isPressed) 0.dp else 5.dp, //Modificación de la elevación
                backgroundColor = Color.White,
                onClick = { isPressed = true },
                shape = RoundedCornerShape(20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 0.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { /* acción */ }) {
                            val batteryPercentage = Widget?.Lightning
                            val batteryColor = gray200

                            Box(
                                modifier = Modifier
                                    .size(52.dp)
                                    .size(40.dp)
                                    .size(width = 10.dp, height = 25.dp)
                                    .background(batteryColor, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_bulb),
                                    contentDescription = "Battery Icon",
                                    modifier = Modifier.size(35.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(0.dp))
                        /*Column {
                            Text(
                                text = "lux",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }*/
                    }

                    // state of the switch
                    var switchOn by remember {
                        mutableStateOf(Widget?.Lightning)
                    }
                    // for moving the thumb
                    val alignment by animateAlignmentAsState(if (switchOn == true) 1f else -1f)

                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Column {
                            Text(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                text = "State",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Spacer(modifier = Modifier.height(5.dp))
                            Box(
                                modifier = Modifier
                                    .size(width = width, height = height)
                                    .border(
                                        width = borderWidth,
                                        color = if (switchOn == true) checkedTrackColor else uncheckedTrackColor,
                                        shape = RoundedCornerShape(percent = cornerSize)
                                    )
                                    .clickable(
                                        indication = null,
                                        interactionSource = interactionSource
                                    ) {
                                        switchOn = !switchOn!!
                                    },
                                contentAlignment = Alignment.Center
                            ) {

                                // this is to add padding at the each horizontal side
                                Box(
                                    modifier = Modifier
                                        .padding(
                                            start = if (switchOn == true) uncheckedGapBetweenThumbAndTrackEdge else checkedGapBetweenThumbAndTrackEdge,
                                            end = if (switchOn == true) uncheckedGapBetweenThumbAndTrackEdge else checkedGapBetweenThumbAndTrackEdge
                                        )
                                        .fillMaxSize(),
                                    contentAlignment = alignment
                                ) {

                                    // thumb with icon
                                    Icon(
                                        imageVector = if (switchOn == true) Icons.Filled.Done else Icons.Filled.Close,
                                        contentDescription = if (switchOn == true) "Enabled" else "Disabled",
                                        modifier = Modifier
                                            .size(size = if (switchOn == true) checkedThumbSize else uncheckedThumbSize)
                                            .background(
                                                color = if (switchOn == true) checkedTrackColor else uncheckedTrackColor,
                                                shape = CircleShape
                                            )
                                            .padding(all = iconInnerPadding),
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .height(80.dp)
                    .width(200.dp)
                    .clickable {}
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .scale(if (isPressed) 0.996f else 1f)//Escala
                    .alpha(if (isPressed) 0.98f else 1f),//Opacidad
                elevation = if (isPressed) 0.dp else 5.dp, //Modificación de la elevación
                backgroundColor = Color.White,
                onClick = { isPressed = true },
                shape = RoundedCornerShape(20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 0.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { /* acción */ }) {
                            val batteryPercentage = Widget?.Lightning
                            val batteryColor = gray200

                            Box(
                                modifier = Modifier
                                    .size(52.dp)
                                    .size(40.dp)
                                    .size(width = 70.dp, height = 25.dp)
                                    .background(gray200, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_load),
                                    contentDescription = "Battery Icon",
                                    modifier = Modifier.size(35.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(0.dp))
                        /*Column {
                            Text(
                                text = "",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                         */
                    }


                    // state of the switch
                    var switchOn by remember {
                        mutableStateOf(Widget?.SensBMP390)
                    }
                    // for moving the thumb
                    val alignment by animateAlignmentAsState(if (switchOn == true) 1f else -1f)
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Column {
                            Text(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                text = "State",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Box(
                                modifier = Modifier
                                    .size(width = width, height = height)
                                    .border(
                                        width = borderWidth,
                                        color = if (switchOn == true) checkedTrackColor else uncheckedTrackColor,
                                        shape = RoundedCornerShape(percent = cornerSize)
                                    )
                                    .clickable(
                                        indication = null,
                                        interactionSource = interactionSource
                                    ) {
                                        switchOn = !switchOn!!
                                    },
                                contentAlignment = Alignment.Center
                            ) {

                                // this is to add padding at the each horizontal side
                                Box(
                                    modifier = Modifier
                                        .padding(
                                            start = if (switchOn == true) uncheckedGapBetweenThumbAndTrackEdge else checkedGapBetweenThumbAndTrackEdge,
                                            end = if (switchOn == true) uncheckedGapBetweenThumbAndTrackEdge else checkedGapBetweenThumbAndTrackEdge
                                        )
                                        .fillMaxSize(),
                                    contentAlignment = alignment
                                ) {

                                    // thumb with icon
                                    Icon(
                                        imageVector = if (switchOn == true) Icons.Filled.Done else Icons.Filled.Close,
                                        contentDescription = if (switchOn == true) "Enabled" else "Disabled",
                                        modifier = Modifier
                                            .size(size = if (switchOn == true) checkedThumbSize else uncheckedThumbSize)
                                            .background(
                                                color = if (switchOn == true) checkedTrackColor else uncheckedTrackColor,
                                                shape = CircleShape
                                            )
                                            .padding(all = iconInnerPadding),
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }








    // gap between switch and the text
    Spacer(modifier = Modifier.height(height = 16.dp))

    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(400)
            isPressed = false
            //navController.navigate(Screens.ChartScreen.route)
        }
    }

}

@Composable
private fun animateAlignmentAsState(
    targetBiasValue: Float
): State<BiasAlignment> {
    val bias by animateFloatAsState(targetBiasValue)
    return derivedStateOf { BiasAlignment(horizontalBias = bias, verticalBias = 0f) }
}