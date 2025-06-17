package com.example.patientdatamanagementsystem.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignupScreenViewModel : ViewModel() {

    var uiState by mutableStateOf(User())
        private set


    fun updateUiState(newUser: User) {
     uiState = uiState.copy(
            email = newUser.email,
            password = newUser.password,
            userType = newUser.userType
        )
    }


}

data class User(val email: String = "", val password: String = "", val userType: String = "")