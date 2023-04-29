package com.kubyapp.agrokuby.presentation.home_screen.components.BarometricData

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
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
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.api.Backend
import com.kubyapp.agrokuby.R
import com.kubyapp.agrokuby.data.model.sensors.Barometric
import com.kubyapp.agrokuby.ui.theme.AmarilloSuave
import com.kubyapp.agrokuby.ui.theme.AzulCielo
import com.kubyapp.agrokuby.ui.theme.BLUE_LIGHT
import com.kubyapp.agrokuby.ui.theme.BatteryFull
import com.kubyapp.agrokuby.ui.theme.GREEN_LIGHT
import com.kubyapp.agrokuby.ui.theme.PURPLE_LIGHT
import com.kubyapp.agrokuby.ui.theme.RegularFont
import com.kubyapp.agrokuby.ui.theme.VerdeMenta
import kotlinx.coroutines.delay
import kotlin.reflect.KProperty


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BarometricDataHolder(

    barometric : Barometric
) {
    var isPressed by remember { mutableStateOf(false) }
    var isPressed2 by remember { mutableStateOf(false) }
    var isPressed3 by remember { mutableStateOf(false) }

    Text(
        modifier = Modifier.padding(top = 25.dp, start = 45.dp, end = 45.dp ),
        text = "Terrain barometric information",
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = Color.Gray,
        fontFamily = RegularFont
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(horizontal = 20.dp)
            .padding(vertical = 6.dp),
        elevation = 5.dp, //Modificación de la elevación
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            // Columna 1
            Card(
                onClick = { isPressed = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .height(160.dp)
                    .weight(1f)
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .scale(if (isPressed) 1f else 1f)//Escala
                    .alpha(if (isPressed) 0.9f else 1f)//Opacidad
                    .clickable { },
                elevation = if (isPressed) 0.dp else 5.dp, //Modificación de la elevación
                backgroundColor = GREEN_LIGHT,
                shape = RoundedCornerShape(20.dp)
            ){
                Column(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 20.dp)
                        .weight(1f),
                    //.offset(x = -30.dp),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_altitude),
                        contentDescription = null,
                        tint = Color.Black
                    )
                    Text(
                        text = "Altitud",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Light
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "${barometric.altitud} m",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }


            // Columna 2
            Card(
                onClick = { isPressed2 = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .height(160.dp)
                    .weight(1f)
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .scale(if (isPressed2) 1f else 1f)//Escala
                    .alpha(if (isPressed2) 0.9f else 1f)//Opacidad
                    .clickable { },
                elevation = if (isPressed2) 0.dp else 5.dp, //Modificación de la elevación
                backgroundColor = VerdeMenta,
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 20.dp)
                        .weight(1f),
                    //.offset(x = -30.dp),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(R.drawable.temp),
                        contentDescription = null,
                        tint = Color.Black
                    )
                    Text(
                        text = "Temperatre",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "${barometric.temperatura} m",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }




            // Columna 2
            Card(
                onClick = { isPressed3 = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .height(160.dp)
                    .weight(1f)
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .scale(if (isPressed3) 1f else 1f)//Escala
                    .alpha(if (isPressed3) 0.9f else 1f)//Opacidad
                    .clickable { },
                elevation = if (isPressed3) 0.dp else 5.dp, //Modificación de la elevación
                backgroundColor = PURPLE_LIGHT,
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 20.dp)
                        .weight(1f),
                    //.offset(x = -30.dp),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_pressure),
                        contentDescription = null,
                        tint = Color.Black
                    )
                    Text(
                        text = "pressure",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "${barometric.presion} m",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
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
    LaunchedEffect(isPressed2) {
        if (isPressed2) {
            delay(400)
            isPressed2 = false
        }
    }
    LaunchedEffect(isPressed3) {
        if (isPressed3) {
            delay(400)
            isPressed3 = false
        }
    }
}



@Preview
@Composable
fun PreviewGreeting() {
    //MositureDataHolder()
}