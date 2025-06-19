package com.example.patientdatamanagementsystem.data

import android.content.Context
import com.example.patientdatamanagementsystem.data.appRepo.AppPreferencesRepo
import com.example.patientdatamanagementsystem.data.appRepo.AuthenticationRepository
import com.example.patientdatamanagementsystem.data.appRepo.AuthenticationRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


interface AppContainer {
    val authenticationRepository: AuthenticationRepository
    val appPreferencesRepo: AppPreferencesRepo
}
class AppContainerImp(private val context: Context): AppContainer{

 private   val auth = FirebaseAuth.getInstance()
 private   val db = FirebaseFirestore.getInstance()
 private   val firebaseService = FirebaseService.getFirebaseInstance(
        auth = auth, db = db
    )

    override val authenticationRepository: AuthenticationRepository by lazy {
        AuthenticationRepositoryImpl(firebaseService = firebaseService)
    }
    override val appPreferencesRepo: AppPreferencesRepo by lazy {
        AppPreferencesRepo(context = context)

    }




}