package com.example.patientdatamanagementsystem.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.patientdatamanagementsystem.AppApplication

object ViewModelInitializer {
    val factory = viewModelFactory {
        initializer {
            AuthenticationViewModel(
                this.appApplication().appContainer.authenticationRepository,
                this.appApplication().appContainer.appPreferencesRepo
            )


        }

    }
}

fun CreationExtras.appApplication(): AppApplication {
    return this[AndroidViewModelFactory.APPLICATION_KEY] as AppApplication
}