package com.kubyapp.agrokuby.presentation.home_screen.components

import android.graphics.drawable.Icon
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kubyapp.agrokuby.R
import com.kubyapp.agrokuby.data.model.sensors.Soil
import com.kubyapp.agrokuby.ui.theme.BLUE_LIGHT
import com.kubyapp.agrokuby.ui.theme.RegularFont
import com.kubyapp.agrokuby.ui.theme.blue
import com.kubyapp.agrokuby.ui.theme.lightBlue
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MositureDataHolder(
    soil: Soil
) {
    var isPressed by remember { mutableStateOf(false) }

    Text(
        modifier = Modifier.padding(top = 15.dp, start = 35.dp, end = 45.dp),
        text = "Soil Moisture",
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = Color.Gray,
        fontFamily = RegularFont
    )

    Card(
        onClick = { isPressed = true },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .height(90.dp)
            .padding(horizontal = 10.dp, vertical = 0.dp)
            .scale(if (isPressed) 0.996f else 1f)//Escala
            .alpha(if (isPressed) 0.98f else 1f)//Opacidad
            .clickable { },
        elevation = if (isPressed) 0.dp else 5.dp, //Modificación de la elevación
        backgroundColor = Color.White,
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .background(color = BLUE_LIGHT, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.humidity_percentage),
                        contentDescription = "Play Icon",
                        modifier = Modifier.size(40.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Humidity",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Second Text",
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
                    Text(modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "average",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Box(
                        modifier = Modifier
                            .width(72.dp)
                            .size(width = 70.dp, height = 25.dp)
                            .background(lightBlue, CircleShape)
                            .align(Alignment.Start),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = "${soil.moisture} %",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal
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
        }
    }
}
@Preview
@Composable
fun PreviewGreeting() {
    //MositureDataHolder()
}

