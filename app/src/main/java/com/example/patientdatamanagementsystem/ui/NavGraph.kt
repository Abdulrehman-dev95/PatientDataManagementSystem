package com.example.patientdatamanagementsystem.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController


sealed class Screen(val route: String){
    object LogIn: Screen("login")
    object SignUp: Screen("signup")
    object Profile: Screen("profile")
    object Home: Screen("home")
}



@Composable
fun NavHost(modifier: Modifier = Modifier, navHostController: NavHostController){






}