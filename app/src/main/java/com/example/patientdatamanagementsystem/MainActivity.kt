package com.example.patientdatamanagementsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.patientdatamanagementsystem.ui.theme.PatientDataManagementSystemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PatientDataManagementSystemTheme {
                PatientDataManagementApp()
            }
        }
    }
}


@Composable
fun PatientDataManagementApp() {
    MainApp()
}