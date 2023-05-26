package com.kubyapp.agrokuby.presentation.home_screen.navigationbar.bar_view


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Language
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kubyapp.agrokuby.R
import com.kubyapp.agrokuby.ui.theme.RegularFont
import com.kubyapp.agrokuby.ui.theme.*
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun settings() {
    val scrollState = rememberScrollState()
    var isPressed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "My account",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            fontFamily = RegularFont,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            onClick = { isPressed = true },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .height(56.dp)
                .padding(horizontal = 0.dp, vertical = 0.dp)
                .scale(if (isPressed) 0.996f else 1f)//Escala
                .alpha(if (isPressed) 0.98f else 1f)//Opacidad
                .clickable { },
            elevation = if (isPressed) 0.dp else 0.dp, //Modificación de la elevación
            backgroundColor = Color.White,
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .heightIn(min = 56.dp)
                    .padding(horizontal = 10.dp, vertical = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = "user information",
                    tint = Color.Black.copy(alpha = 1f),
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "user information",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.size(24.dp)
                )
            }
        }




        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "application settings",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontFamily = RegularFont,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            onClick = { isPressed = true },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .height(56.dp)
                .padding(horizontal = 0.dp, vertical = 0.dp)
                .scale(if (isPressed) 0.996f else 1f)//Escala
                .alpha(if (isPressed) 0.98f else 1f)//Opacidad
                .clickable { },
            elevation = if (isPressed) 0.dp else 0.dp, //Modificación de la elevación
            backgroundColor = Color.White,
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .heightIn(min = 56.dp)
                    .padding(horizontal = 10.dp, vertical = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_globe),
                    contentDescription = "Language settings",
                    tint = Color.Black.copy(alpha = 1f),
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Language settings",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.size(24.dp)
                )
            }
        }


        Card(
            onClick = { isPressed = true },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .height(56.dp)
                .padding(horizontal = 0.dp, vertical = 0.dp)
                .scale(if (isPressed) 0.996f else 1f)//Escala
                .alpha(if (isPressed) 0.98f else 1f)//Opacidad
                .clickable { },
            elevation = if (isPressed) 0.dp else 0.dp, //Modificación de la elevación
            backgroundColor = Color.White,
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .heightIn(min = 56.dp)
                    .padding(horizontal = 10.dp, vertical = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_mobile),
                    contentDescription = "App permissions",
                    tint = Color.Black.copy(alpha = 1f),
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "App permissions",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.size(24.dp)
                )
            }
        }


        Card(
            onClick = { isPressed = true },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .height(56.dp)
                .padding(horizontal = 0.dp, vertical = 0.dp)
                .scale(if (isPressed) 0.996f else 1f)//Escala
                .alpha(if (isPressed) 0.98f else 1f)//Opacidad
                .clickable { },
            elevation = if (isPressed) 0.dp else 0.dp, //Modificación de la elevación
            backgroundColor = Color.White,
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .heightIn(min = 56.dp)
                    .padding(horizontal = 10.dp, vertical = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_brackets),
                    contentDescription = "routine programming",
                    tint = Color.Black.copy(alpha = 1f),
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Routine programming",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Card(
            onClick = { isPressed = true },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .height(56.dp)
                .padding(horizontal = 0.dp, vertical = 0.dp)
                .scale(if (isPressed) 0.996f else 1f)//Escala
                .alpha(if (isPressed) 0.98f else 1f)//Opacidad
                .clickable { },
            elevation = if (isPressed) 0.dp else 0.dp, //Modificación de la elevación
            backgroundColor = Color.White,
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .heightIn(min = 56.dp)
                    .padding(horizontal = 10.dp, vertical = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notifications),
                    contentDescription = "notifications",
                    tint = Color.Black.copy(alpha = 1f),
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Notifications",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.size(24.dp)
                )
            }
        }


        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Help me",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontFamily = RegularFont,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            onClick = { isPressed = true },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .height(56.dp)
                .padding(horizontal = 0.dp, vertical = 0.dp)
                .scale(if (isPressed) 0.996f else 1f)//Escala
                .alpha(if (isPressed) 0.98f else 1f)//Opacidad
                .clickable { },
            elevation = if (isPressed) 0.dp else 0.dp, //Modificación de la elevación
            backgroundColor = Color.White,
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .heightIn(min = 56.dp)
                    .padding(horizontal = 10.dp, vertical = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_chat),
                    contentDescription = "customer service",
                    tint = Color.Black.copy(alpha = 1f),
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Customer service",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.size(24.dp)
                )
            }
        }


        Card(
            onClick = { isPressed = true },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .height(56.dp)
                .padding(horizontal = 0.dp, vertical = 0.dp)
                .scale(if (isPressed) 0.996f else 1f)//Escala
                .alpha(if (isPressed) 0.98f else 1f)//Opacidad
                .clickable { },
            elevation = if (isPressed) 0.dp else 0.dp, //Modificación de la elevación
            backgroundColor = Color.White,
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .heightIn(min = 56.dp)
                    .padding(horizontal = 10.dp, vertical = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_users),
                    contentDescription = "Community & forums",
                    tint = Color.Black.copy(alpha = 1f),
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Community & forums",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.size(24.dp)
                )
            }
        }


        Card(
            onClick = { isPressed = true },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .height(56.dp)
                .padding(horizontal = 0.dp, vertical = 0.dp)
                .scale(if (isPressed) 0.996f else 1f)//Escala
                .alpha(if (isPressed) 0.98f else 1f)//Opacidad
                .clickable { },
            elevation = if (isPressed) 0.dp else 0.dp, //Modificación de la elevación
            backgroundColor = Color.White,
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .heightIn(min = 56.dp)
                    .padding(horizontal = 10.dp, vertical = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notebook),
                    contentDescription = "Documentacion",
                    tint = Color.Black.copy(alpha = 1f),
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Documentation",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.size(24.dp)
                )
            }
        }


        Spacer(modifier = Modifier.height(104.dp))
        LaunchedEffect(isPressed) {
            if (isPressed) {
                delay(400)
                isPressed = false
            }
        }


    }
}