package com.example.patientdatamanagementsystem.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.patientdatamanagementsystem.R
import com.example.patientdatamanagementsystem.ui.theme.PatientDataManagementSystemTheme
import com.example.patientdatamanagementsystem.ui.theme.bodyFontFamily


@Composable
fun LoginLayout(
    onLogin: (String, String) -> Unit,
    onSignUpClick: () -> Unit,
    loginViewModel: AuthenticationViewModel,
    onValueChange: (User) -> Unit,
    onLoginRetryClick: () -> Unit,
    navHostController: NavHostController
) {
    val uiState = loginViewModel.uiState
    val authState = loginViewModel.authState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(32.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
            Image(
                painter = painterResource(R.drawable.image_doctor),
                contentDescription = null
            )
        }

        when (authState.value) {
            0 -> LoadingScreen()
            1 -> OnLoginSuccess {
                navHostController.navigate(
                    Screen.Home.route
                ) {
                    popUpTo(Screen.LogIn.route) {
                        inclusive = true
                    }
                }
            }

            2 -> ErrorScreen {
                onLoginRetryClick()
            }
        }




        AnimatedVisibility(
            authState.value == 3
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.Center,
            ) {

                Text(
                    "Welcome to BlockCare Please Login",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(
                        Alignment.CenterHorizontally
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = {
                        onValueChange(
                            uiState.copy(
                                email = it
                            )
                        )
                    },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        autoCorrectEnabled = false,
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ), textStyle = LocalTextStyle.current.copy(
                        fontFamily = bodyFontFamily,
                    ),
                    shape = RoundedCornerShape(16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = uiState.password,
                    onValueChange = {
                        onValueChange(
                            uiState.copy(
                                password = it
                            )
                        )
                    },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done,
                        autoCorrectEnabled = true
                    ), textStyle = LocalTextStyle.current.copy(
                        fontFamily = bodyFontFamily,
                    ), shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))



                Spacer(modifier = Modifier.height(64.dp))


                Button(
                    onClick = { onLogin(uiState.email, uiState.password) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Login", fontFamily = bodyFontFamily)
                }

                TextButton(
                    onClick = onSignUpClick,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        "Don't have an account? Sign up",
                        fontFamily = bodyFontFamily,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun OnLoginSuccess(onNavigate: () -> Unit) {
    val rememberSnackBarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(Unit) {
        rememberSnackBarHostState.showSnackbar(
            message = "Login Successful",
            duration = SnackbarDuration.Short
        )
        onNavigate()
    }
    Scaffold(snackbarHost = { SnackbarHost(hostState = rememberSnackBarHostState) }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginScreen() {
    PatientDataManagementSystemTheme {
        LoginLayout(
            onLogin = { _, _ -> },
            onSignUpClick = {},
            onValueChange = {},
            onLoginRetryClick = {},
            loginViewModel = TODO(),
            navHostController = TODO()
        )
    }

}