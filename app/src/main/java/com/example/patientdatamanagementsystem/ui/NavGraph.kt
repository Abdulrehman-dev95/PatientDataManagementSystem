package com.example.patientdatamanagementsystem.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


sealed class Screen(val route: String) {
    object LogIn : Screen("login")
    object SignUp : Screen("signup")
    object Profile : Screen("profile")
    object Home : Screen("home")
}


@Composable
fun BlockCareNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    authenticationViewModel: AuthenticationViewModel = viewModel(
        factory = ViewModelInitializer.factory
    )
) {

    NavHost(
        navController = navHostController,
        startDestination = if (authenticationViewModel.currentRole == "") Screen.LogIn.route else Screen.Home.route,
        modifier = modifier

    ) {
        composable(Screen.LogIn.route) {

            LoginLayout(
                onLogin = { email, password ->
                    authenticationViewModel.login(email, password)
                },
                onSignUpClick = {
                    navHostController.navigate(Screen.SignUp.route)
                },
                onValueChange = {
                    authenticationViewModel.updateUiState(it)
                },
                onLoginRetryClick = {
                    authenticationViewModel.changeAuthSate(3)
                    navHostController.navigate(Screen.LogIn.route)
                },
                navHostController = navHostController,
                loginViewModel = authenticationViewModel
            )
        }

        composable(Screen.SignUp.route) {
            SignupScreen(
                onSignUpClick = { email, password, userType ->
                    authenticationViewModel.signUp(email, password, userType)
                },
                onSignUpRetryClick = {
                    authenticationViewModel.changeAuthSate(3)
                    navHostController.navigate(Screen.SignUp.route)
                },
                onLoginClick = {
                    navHostController.navigate(Screen.LogIn.route)
                },
                signUpViewModel = authenticationViewModel,
                navHostController = navHostController
            )
        }

        composable(
            Screen.Profile.route

        ) {}


        composable(Screen.Home.route) {
            PatientHomeScreen()
        }


    }
}