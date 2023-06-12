package com.kubyapp.agrokuby.presentation.home_screen.navigationbar.bar_view.settings_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kubyapp.agrokuby.ui.theme.RegularFont
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun userInfoScreen(){
    Text(
        modifier = Modifier.padding(top = 10.dp, start = 35.dp, end = 35.dp ),
        text = "information for the robot",
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = Color.Gray,
        fontFamily = RegularFont
    )
}