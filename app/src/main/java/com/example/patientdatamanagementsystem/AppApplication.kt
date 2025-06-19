package com.example.patientdatamanagementsystem

import android.app.Application
import com.example.patientdatamanagementsystem.data.AppContainer
import com.example.patientdatamanagementsystem.data.AppContainerImp

class AppApplication: Application() {
    lateinit var appContainer: AppContainer
    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainerImp(this)
    }
}