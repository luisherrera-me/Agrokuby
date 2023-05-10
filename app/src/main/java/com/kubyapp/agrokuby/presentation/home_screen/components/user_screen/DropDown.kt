package com.kubyapp.agrokuby.presentation.home_screen.components.user_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.common.io.Files.append
import com.kubyapp.agrokuby.R
import com.kubyapp.agrokuby.data.model.user.UserInfo
import com.kubyapp.agrokuby.ui.theme.ORANGE_LIGHT
import com.kubyapp.agrokuby.ui.theme.RegularFont
import com.kubyapp.agrokuby.ui.theme.TEAL_LIGHT
import com.kubyapp.agrokuby.ui.theme.gray100
import com.kubyapp.agrokuby.ui.theme.lightRed

@Composable
fun DropDown(
    userInfo: UserInfo,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    expanded:Boolean,
    onDismissClick: () -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissClick,
        modifier = modifier,
        content = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 5.dp, top = 5.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .align(Alignment.CenterHorizontally),
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Gray)) {
                            append("Hello ")
                        }
                        withStyle(style = SpanStyle(color = Color.Black,fontWeight = FontWeight.Bold)) {
                            append(userInfo.username)
                        }
                    },
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    fontFamily = RegularFont,
                    textAlign = TextAlign.Center
                )

                Divider(
                    color = gray100,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                DropdownMenuItem(
                    onClick = {
                        // Acción para ir al perfil de usuario
                    },
                    modifier = Modifier.padding(vertical = 0.dp)
                ) {
                    Row() {
                        Icon(
                            painter = painterResource(id = R.drawable.account_circle),
                            contentDescription = "Mi Perfil",
                            modifier = Modifier.size(20.dp),
                            tint = TEAL_LIGHT
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Mi Perfil",
                            fontFamily = RegularFont,
                            textAlign = TextAlign.Center)
                    }
                }
                DropdownMenuItem(
                    onClick = {
                        // Acción para recibir ayuda
                    },
                    modifier = Modifier.padding(vertical = 0.dp)
                ) {
                    Row() {
                        Icon(
                            painter = painterResource(id = R.drawable.help),
                            contentDescription = "Ayuda",
                            modifier = Modifier.size(20.dp),
                            tint = ORANGE_LIGHT
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Ayuda",
                            fontFamily = RegularFont,
                            textAlign = TextAlign.Center)
                    }
                }
                Divider(
                    color = gray100,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                DropdownMenuItem(
                    onClick = onClick,
                    modifier = Modifier.padding(vertical = 0.dp)
                ) {
                    Row() {
                        Icon(
                            painter = painterResource(id = R.drawable.logout),
                            contentDescription = "Cerrar sesión",
                            modifier = Modifier.size(20.dp),
                            tint = lightRed
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Cerrar sesión",
                            fontFamily = RegularFont,
                            textAlign = TextAlign.Center)
                    }
                }
            }
        }
    )
}
