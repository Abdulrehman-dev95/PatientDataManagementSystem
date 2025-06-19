package com.example.patientdatamanagementsystem.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patientdatamanagementsystem.data.appRepo.AppPreferencesRepo
import com.example.patientdatamanagementsystem.data.appRepo.AuthenticationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AuthenticationViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val appPreferencesRepo: AppPreferencesRepo
) : ViewModel() {

    var uiState by mutableStateOf(User())
        private set

    fun updateUiState(newUser: User) {
        uiState = uiState.copy(
            email = newUser.email,
            password = newUser.password,
            userType = newUser.userType
        )
    }


    private val _authState = MutableStateFlow(0)
    val authState = _authState.asStateFlow()


    private suspend fun getUserRole() {
        val role = appPreferencesRepo.role.first()
        if (
            role != ""
        ) {
            _authState.value = 1
        } else {
            _authState.value = 2
        }
    }

    init {
        viewModelScope.launch {
            getUserRole()
        }
    }

}

data class User(val email: String = "", val password: String = "", val userType: String = "")