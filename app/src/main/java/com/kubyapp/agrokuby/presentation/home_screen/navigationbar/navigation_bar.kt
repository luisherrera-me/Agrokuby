package com.kubyapp.agrokuby.presentation.home_screen.navigationbar

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.ViewInAr
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kubyapp.agrokuby.R
import com.kubyapp.agrokuby.ui.theme.DEFAULT_PADDING
import com.kubyapp.agrokuby.ui.theme.FluidBottomNavigationTheme
import com.kubyapp.agrokuby.ui.theme.ORANGE_LIGHT
import com.kubyapp.agrokuby.ui.theme.RegularFont
import com.kubyapp.agrokuby.ui.theme.gray300
import com.kubyapp.agrokuby.ui.theme.gray400
import com.kubyapp.agrokuby.ui.theme.gray600
import com.kubyapp.agrokuby.ui.theme.gray800

import kotlin.math.PI
import kotlin.math.sin

@RequiresApi(Build.VERSION_CODES.S)
private fun getRenderEffect(): RenderEffect {
    val blurEffect = RenderEffect
        .createBlurEffect(80f, 80f, Shader.TileMode.MIRROR)

    val alphaMatrix = RenderEffect.createColorFilterEffect(
        ColorMatrixColorFilter(
            ColorMatrix(
                floatArrayOf(
                    1f, 0f, 0f, 0f, 0f,
                    0f, 1f, 0f, 0f, 0f,
                    0f, 0f, 1f, 0f, 0f,
                    0f, 0f, 0f, 50f, -5000f
                )
            )
        )
    )

    return RenderEffect
        .createChainEffect(alphaMatrix, blurEffect)
}
@Composable
fun navigation_bar(
    navController: NavController,
    menu_items: List<Items_menu>
) {
    val isMenuExtended = remember { mutableStateOf(false) }

    val NavController = rememberNavController()
    val ScaffoldState = rememberScaffoldState()
    val Scope = rememberCoroutineScope()

    val fabAnimationProgress by animateFloatAsState(
        targetValue = if (isMenuExtended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing
        )
    )

    val clickAnimationProgress by animateFloatAsState(
        targetValue = if (isMenuExtended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 400,
            easing = LinearEasing
        )
    )

    val renderEffect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        getRenderEffect().asComposeRenderEffect()
    } else {
        null
    }

    navigation_bar(
        navController = NavController,
        renderEffect = renderEffect,
        fabAnimationProgress = fabAnimationProgress,
        clickAnimationProgress = clickAnimationProgress,

    ) {
        isMenuExtended.value = isMenuExtended.value.not()
    }
}


@Composable
fun navigation_bar(
    navController: NavController,
    renderEffect: androidx.compose.ui.graphics.RenderEffect?,
    fabAnimationProgress: Float = 0f,
    clickAnimationProgress: Float = 0f,
    toggleAnimation: () -> Unit = { }

) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val navigacion_item = listOf(
        Items_menu.Pantallas1,
        Items_menu.Pantallas2,
        Items_menu.Pantallas3

    )
    Box(
        Modifier
            .fillMaxSize()
            .padding(bottom = 4.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        CustomBottomNavigation(navController, navigacion_item)
        Circle(
            color = MaterialTheme.colors.primary.copy(alpha = 0.5f),
            animationProgress = 0.5f
        )

        FabGroup(renderEffect = renderEffect, animationProgress = fabAnimationProgress)
        FabGroup(
            renderEffect = null,
            animationProgress = fabAnimationProgress,
            toggleAnimation = toggleAnimation
        )
        Circle(
            color = Color.White,
            animationProgress = clickAnimationProgress
        )
    }
}



@Composable
fun Circle(color: Color, animationProgress: Float) {
    val animationValue = sin(PI * animationProgress).toFloat()

    Box(
        modifier = Modifier
            .padding(DEFAULT_PADDING.dp)
            .size(56.dp)
            .scale(2 - animationValue)
            .border(
                width = 2.dp,
                color = Color.Transparent,
                shape = CircleShape
            )
    )
}

@Composable
fun currentRouter(navController: NavHostController):String?{
    val entrada by navController.currentBackStackEntryAsState()
    return entrada?.destination?.route
}



@Composable
fun CustomBottomNavigation(
    navController: NavController,
    menu_items: List<Items_menu>
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(100.dp)
            .padding(horizontal = 40.dp)
    ) {
        BottomAppBar(
            modifier = Modifier
                .weight(1f)
                .height(300.dp)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
            elevation = 0.dp,
            backgroundColor = Color.Transparent
        ) {
            BottomNavigation(
                modifier = Modifier
                    .weight(1f)
                    .height(65.dp)
                    .clip(RoundedCornerShape(
                        topStart = 40.dp,
                        topEnd = 40.dp,
                        bottomStart = 40.dp,
                        bottomEnd = 40.dp
                    )),
                backgroundColor = Color.Black,
                elevation = 2.dp
            ) {
                val currentRoute = currentRouter(navController = navController as NavHostController)
                menu_items.forEach { item ->
                    BottomNavigationItem(
                        selected = currentRoute == item.ruta,
                        onClick = { navController.navigate(item.ruta) },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.title,
                                tint = item.iconColor
                            )
                        },
                        label = {
                            Text(
                                item.title,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontFamily = RegularFont
                            )
                        },
                        alwaysShowLabel = false
                    )
                }
            }
        }

    }
}




@Composable
fun FabGroup(
    animationProgress: Float = 0f,
    renderEffect: androidx.compose.ui.graphics.RenderEffect? = null,
    toggleAnimation: () -> Unit = { }
) {
    Box(
        Modifier
            .fillMaxSize()
            .graphicsLayer { this.renderEffect = renderEffect }
            .padding(bottom = 75.dp),
        contentAlignment = Alignment.BottomCenter
    ) {

        AnimatedFab(
            icon = Icons.Default.ViewInAr,
            modifier = Modifier
                .padding(
                    PaddingValues(
                        bottom = 72.dp,
                        end = 210.dp
                    ) * FastOutSlowInEasing.transform(0f, 0.8f, animationProgress)
                ),
            opacity = LinearEasing.transform(0.2f, 0.7f, animationProgress)
        )

        AnimatedFab(
            icon = Icons.Default.List,
            modifier = Modifier.padding(
                PaddingValues(
                    bottom = 88.dp,
                ) * FastOutSlowInEasing.transform(0.1f, 0.9f, animationProgress)
            ),
            opacity = LinearEasing.transform(0.3f, 0.8f, animationProgress)
        )

        AnimatedFab(
            icon = Icons.Default.Storage,
            modifier = Modifier.padding(
                PaddingValues(
                    bottom = 72.dp,
                    start = 210.dp
                ) * FastOutSlowInEasing.transform(0.2f, 1.0f, animationProgress)
            ),
            opacity = LinearEasing.transform(0.4f, 0.9f, animationProgress)
        )

        AnimatedFab(
            modifier = Modifier
                .scale(1f - LinearEasing.transform(0.5f, 0.85f, animationProgress)),
        )

        AnimatedFab(
            icon = Icons.Default.Add,

            modifier = Modifier
                .rotate(
                    225 * FastOutSlowInEasing
                        .transform(0.35f, 0.65f, animationProgress)
                ),
            onClick = toggleAnimation,
            backgroundColor = Color.Transparent
        )
    }
}

@Composable
fun AnimatedFab(
    modifier: Modifier,
    icon: ImageVector? = null,
    opacity: Float = 1f,
    backgroundColor: Color = gray800,
    onClick: () -> Unit = {}
) {

    FloatingActionButton(
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp),
        backgroundColor = backgroundColor,
        modifier = modifier.scale(1f)
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                tint = ORANGE_LIGHT.copy(alpha = opacity)
            )
        }
    }
}


@Composable
@Preview()
private fun MainScreenPreview() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val navigacion_item = listOf(
        Items_menu.Pantallas1,
        Items_menu.Pantallas2,
        Items_menu.Pantallas3

    )
    FluidBottomNavigationTheme {
        navigation_bar(navController, navigacion_item)
    }
}