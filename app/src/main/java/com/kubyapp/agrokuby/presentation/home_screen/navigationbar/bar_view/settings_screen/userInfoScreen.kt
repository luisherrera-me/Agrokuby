package com.kubyapp.agrokuby.presentation.home_screen.navigationbar.bar_view.settings_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Icon
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kubyapp.agrokuby.ui.theme.RegularFont
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.kubyapp.agrokuby.R
import com.kubyapp.agrokuby.presentation.home_screen.components.user_screen.UserViewModel
import com.kubyapp.agrokuby.ui.theme.blue
import com.kubyapp.agrokuby.ui.theme.gray100
import com.kubyapp.agrokuby.ui.theme.gray200
import com.kubyapp.agrokuby.ui.theme.gray300
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun userInfoScreen(
    userData: UserViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()
    val borderWidth = 2.dp
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val customFont = FontFamily(Font(R.font.poppinsmedium))
    var isPressed by remember { mutableStateOf(false) }
    val user by userData.UserStatus.collectAsState()
    val rainbowColorsBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xff9fb78f),
                Color(0xFF5e8d61),
                Color(0xff9fb78f),


                )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = "User information",
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.h5,
            color = Color.Gray,
            fontFamily = RegularFont,
            modifier = Modifier.padding(top = 10.dp)
        )

        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen
            AsyncImage(
                model = user.User?.photoUser,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .border(
                        BorderStroke(borderWidth, rainbowColorsBrush),
                        CircleShape
                    )
            )

            // Espacio entre la imagen y el Card
            Spacer(modifier = Modifier.width(16.dp))

            // Card
            Card(
                onClick = { isPressed = true },
                modifier = Modifier
                    .weight(1f) // Ocupa el espacio restante
                    .height(56.dp)
                    .scale(if (isPressed) 0.996f else 1f)
                    .alpha(if (isPressed) 0.98f else 1f)
                    .clickable { },
                elevation = if (isPressed) 0.dp else 0.dp,
                backgroundColor = Color(0xFFf0e89e),
                shape = RoundedCornerShape(20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    user.User?.let {
                        Text(
                            text = "Hello " + it.username,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontFamily = RegularFont
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Edit informacion",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            fontFamily = RegularFont,
            modifier = Modifier.padding(top = 0.dp)
        )
        Card(
            modifier = Modifier
                .height(400.dp)
                .scale(1f)
                .alpha(1f),
            elevation = 1.dp,
            backgroundColor = gray300,
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "User name",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    fontFamily = RegularFont,
                    modifier = Modifier.padding(bottom = 0.dp)
                )

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_user),
                            contentDescription = "Email Icon"
                        )
                    },
                    value = email,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = gray100,
                        cursorColor = Color.Black,
                        disabledLabelColor = blue,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    onValueChange = {
                        email = it
                    },
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true,
                    textStyle = TextStyle(fontFamily = customFont),
                    placeholder = {
                        user.User?.let { Text(text = it.username) }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email
                    )
                )




                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Phone number",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontFamily = RegularFont,
                    modifier = Modifier.padding(bottom = 0.dp)
                )

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_mobile),
                            contentDescription = "Email Icon"
                        )
                    },
                    value = email,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = gray100,
                        cursorColor = Color.Black,
                        disabledLabelColor = blue,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    onValueChange = {
                        email = it
                    },
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true,
                    textStyle = TextStyle(fontFamily = customFont),
                    placeholder = {
                        Text(text = "+57 3187903052")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Email",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    fontFamily = RegularFont,
                    modifier = Modifier.padding(bottom = 0.dp)
                )

                var isExpanded by remember { mutableStateOf(false) }


                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_mail),
                            contentDescription = "Email Icon"
                        )
                    },
                    value = email,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = gray100,
                        cursorColor = Color.Black,
                        disabledLabelColor = blue,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    onValueChange = {
                        email = it
                    },
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true,
                    textStyle = TextStyle(fontFamily = customFont),
                    placeholder = {
                        Text(text = "Email")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email
                    )
                )


                Button(
                    onClick = {


                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, start = 30.dp, end = 30.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Black, contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(text = "Sign In", color = Color.White, modifier = Modifier.padding(7.dp))
                }

            }
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