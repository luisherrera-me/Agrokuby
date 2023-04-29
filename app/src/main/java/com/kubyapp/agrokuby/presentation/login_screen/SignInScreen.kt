package com.kubyapp.agrokuby.presentation.login_screen

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import com.kubyapp.agrokuby.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

import com.kubyapp.agrokuby.data.Constant.ServerClient
import com.kubyapp.agrokuby.navigation.Screens
import com.kubyapp.agrokuby.ui.theme.RegularFont
import com.kubyapp.agrokuby.ui.theme.blue
import com.kubyapp.agrokuby.ui.theme.lightBlue
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val googleSignInState = viewModel.googleState.value
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()){
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                var credentials = GoogleAuthProvider.getCredential(result.idToken,null)
                viewModel.googleSignIn(credentials)
            }catch (it: ApiException){
                print("Error Vista: ${it}")
            }
        }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signInState.collectAsState(initial = null)
    val customFont = FontFamily(Font(R.font.poppinsmedium))
    var showPassword by remember { mutableStateOf(false) }
    val isUserExist = viewModel.currentUserExist.collectAsState(initial = true)
    /*Image(
        painter = painterResource(id = R.drawable.lg_fondo),
        contentDescription = "Imagen",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .blur(radius = 100.dp) // Radio del desenfoque

    )*/

    Image(
        painter = painterResource(id = R.drawable.fondo),
        contentDescription = "Imagen",
        modifier = Modifier.alpha(0.1f),
        contentScale = ContentScale.Crop
    )

    LaunchedEffect(key1 = Unit) {
        if (isUserExist.value) {
            navController.popBackStack()
            navController.navigate(
                Screens.HomeScreen.route
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(start = 30.dp, end = 30.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.lg_agrokuby),
            contentDescription = "Agro Kuby",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .size(200.dp)
                //.aspectRatio(16f/9f)
                .padding(0.dp, 40.dp, 0.dp, 20.dp)
                .fillMaxWidth()
        )
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = "log in",
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            color = Color.Black,
            fontFamily = RegularFont
        )
        Text(
            modifier = Modifier.padding(bottom = 5.dp),
            text = "Enter your access credentials",
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            color = Color.Gray,
            fontFamily = RegularFont
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email Icon"
                )
            },
            value = email,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            onValueChange = {
                email = it
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            textStyle = TextStyle(fontFamily = customFont),
            placeholder = {
                Text(text = "Email")
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            )
        )


        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(fontFamily = customFont),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = "Lock Icon"
                )
            }, trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                        contentDescription = if (showPassword) "Show Password" else "Hide Password",
                        tint = if (showPassword) Color.Black else Color.Gray

                    )
                }
            },
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            value = password,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                password = it
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            placeholder = {
                Text(text = "Password")
            }
        )

        Button(
            onClick = {
                scope.launch {
                    viewModel.loginUser(email, password)
                }
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
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            if (state.value?.isLoading == true) {
                LaunchedEffect(key1 = state.value?.isError) {

                }
                }

        }
        Text(
            modifier = Modifier
                .padding(15.dp)
                .clickable {
                    navController.navigate(Screens.SignUpScreen.route)
                },
            text = "New User? Sign Up ",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontFamily = RegularFont
        )
        Text(
            modifier = Modifier
                .padding(
                    top = 0.dp,
                ),
            text = "Or connect with",
            fontWeight = FontWeight.Medium, color = Color.White
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = {
                val gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestIdToken(ServerClient)
                    .build()

                val googleSingInClient = GoogleSignIn.getClient(context, gso)

                launcher.launch(googleSingInClient.signInIntent)

            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google Icon",
                    modifier = Modifier.size(50.dp),
                    tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            IconButton(onClick = {

            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = "Facebook Icon",
                    modifier = Modifier.size(50.dp),
                    tint = Color.Unspecified
                )
            }


            LaunchedEffect(key1 = state.value?.isSuccess) {
                scope.launch {
                    if (state.value?.isSuccess?.isNotEmpty() == true) {
                        val success = state.value?.isSuccess
                        Toast.makeText(context, "${success}", Toast.LENGTH_LONG).show()
                        navController.navigate(Screens.HomeScreen.route)
                    }
                }
            }

            LaunchedEffect(key1 = state.value?.isError) {
                scope.launch {
                    if (state.value?.isError?.isNotEmpty() == true) {
                        val error = state.value?.isError
                        Toast.makeText(context, "${error}", Toast.LENGTH_LONG).show()
                    }
                }
            }

            LaunchedEffect(key1 = googleSignInState.success) {
                scope.launch {
                    if (googleSignInState.success != null) {
                        Toast.makeText(context, "Sign In Success", Toast.LENGTH_LONG).show()
                        navController.navigate(Screens.HomeScreen.route)
                    }
                }
            }

        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            if (googleSignInState.loading){
                CircularProgressIndicator()
            }
        }

        Image(
            painter = painterResource(id = R.drawable.by),
            contentDescription = "Agro Kuby",
            contentScale = ContentScale.Inside,
            modifier = Modifier.size(200.dp)
                //.aspectRatio(16f/9f)
                .padding(0.dp, 50.dp, 0.dp, 0.dp)
                .fillMaxWidth()
        )


    }
}