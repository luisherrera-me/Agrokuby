package com.kubyapp.agrokuby.presentation.Splash_Screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kubyapp.agrokuby.navigation.Screens
import com.kubyapp.agrokuby.presentation.login_screen.SignInViewModel
import com.kubyapp.agrokuby.ui.theme.Purple700
import com.kubyapp.agrokuby.ui.theme.background
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(
    navController: NavHostController,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val isUserExist = viewModel.currentUserExist.collectAsState(initial = true)
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 5f else 5f,
        animationSpec = tween(
            durationMillis = 500
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        if (isUserExist.value) {
            navController.popBackStack()
            navController.navigate(
                Screens.HomeScreen.route
            )
        }else{
            navController.navigate(Screens.SignInScreen.route)
        }
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float) {
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else background)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = com.kubyapp.agrokuby.R.drawable.lg_agrokuby),
            contentDescription = "Agro Kuby",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .size(200.dp)
                //.aspectRatio(16f/9f)
                .padding(0.dp, 40.dp, 0.dp, 20.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    Splash(alpha = 1f)
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun SplashScreenDarkPreview() {
    Splash(alpha = 1f)
}