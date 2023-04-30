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
import androidx.compose.material.Card
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
import com.kubyapp.agrokuby.data.model.sensors.Soil
import com.kubyapp.agrokuby.ui.theme.Melocoton
import com.kubyapp.agrokuby.ui.theme.RegularFont
import com.kubyapp.agrokuby.ui.theme.TempPulple
import com.kubyapp.agrokuby.ui.theme.lightBlue


@Composable
fun TempDataHolder(
    soil: Soil
) {
    Text(
        modifier = Modifier.padding(top = 20.dp, start = 45.dp, end = 45.dp ),
        text = "soil temperature",
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = Color.Gray,
        fontFamily = RegularFont
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 20.dp)
            .padding(vertical = 6.dp),
        elevation = 5.dp,
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
                        .size(width = 70.dp, height = 25.dp)
                        .clickable(onClick = {

                        })
                        .background(Melocoton, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.temp),
                        contentDescription = "Play Icon",
                        modifier = Modifier.size(40.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Temperature",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        fontWeight = FontWeight.Medium,
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
                            .clickable(onClick = {

                            })
                            .background(lightBlue, CircleShape)
                            .align(Alignment.Start),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = "${soil.temp} °C",
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


}

