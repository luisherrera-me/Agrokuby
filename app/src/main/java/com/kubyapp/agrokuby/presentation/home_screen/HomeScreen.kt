package com.kubyapp.agrokuby.presentation.home_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kubyapp.agrokuby.R
import com.kubyapp.agrokuby.navigation.Screens
import com.kubyapp.agrokuby.presentation.home_screen.components.user_screen.DropDown
import com.kubyapp.agrokuby.presentation.home_screen.components.user_screen.ProfilePicture
import com.kubyapp.agrokuby.presentation.home_screen.components.user_screen.UserViewModel
import com.kubyapp.agrokuby.presentation.home_screen.navigationbar.CustomBottomNavigation
import com.kubyapp.agrokuby.presentation.home_screen.navigationbar.DrawerBody
import com.kubyapp.agrokuby.presentation.home_screen.navigationbar.DrawerHeader
import com.kubyapp.agrokuby.presentation.home_screen.navigationbar.Items_menu
import com.kubyapp.agrokuby.presentation.home_screen.navigationbar.MenuItem
import com.kubyapp.agrokuby.presentation.home_screen.navigationbar.bar_view.NavegacionHost
import com.kubyapp.agrokuby.ui.theme.gray300
import kotlinx.coroutines.launch


@Composable
fun currentRouter(navController: NavHostController): String? {
    val entrada by navController.currentBackStackEntryAsState()
    return entrada?.destination?.route
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    userData: UserViewModel = hiltViewModel(),
    navController: NavHostController
) {


    val navController = rememberNavController()
    val scope = rememberCoroutineScope()


    val navigacion_item = listOf(
        Items_menu.Pantallas1,
        Items_menu.Pantallas2,
        Items_menu.Pantallas3

    )


    val user by userData.UserStatus.collectAsState()
    var expanded by remember { mutableStateOf(false) }

    Log.d("TAG", "HomeScreen: ${user.User}")

    val scaffoldState = rememberScaffoldState()


    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = Color.Transparent,
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                    .padding(start = 0.dp, end = 0.dp),
                backgroundColor = Color.White,
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = "Navegar hacia atrás",
                            modifier = Modifier.size(36.dp)
                        )
                    }
                },
                actions = {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.lg_agrokuby),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(150.dp)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        user.User?.let {
                            ProfilePicture(onExpandChange = {
                                expanded = !expanded
                            }, userInfo = it)
                        }
                        //dropdownmenu
                        user.User?.let {
                            DropDown(
                                userInfo = it, onClick = {
                                    viewModel.signOut()
                                    navController.popBackStack()
                                    navController.navigate(Screens.SignInScreen.route)
                                }, modifier = Modifier
                                    .align(Alignment.End)
                                    .background(gray300),
                                expanded = expanded,
                                onDismissClick = {
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = listOf(
                    MenuItem(
                        id = "home",
                        title = "Home",
                        contentDescription = "Go to home screen",
                        icon = Icons.Default.Home
                    ),
                    MenuItem(
                        id = "settings",
                        title = "Settings",
                        contentDescription = "Go to settings screen",
                        icon = Icons.Default.Settings
                    ),
                    MenuItem(
                        id = "help",
                        title = "Help",
                        contentDescription = "Get help",
                        icon = Icons.Default.Info
                    ),
                ),
                onItemClick = {
                    println("Clicked on ${it.title}")
                }
            )
        }
    ) {


        Scaffold(
            bottomBar = { CustomBottomNavigation(navController, navigacion_item) }
        ) {
            NavegacionHost(navController)
        }
    }
}
