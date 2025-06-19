package com.example.patientdatamanagementsystem.data.appRepo

import com.example.patientdatamanagementsystem.data.FirebaseService

interface AuthenticationRepository {
    suspend fun signUp(email: String, password: String, role: String): Result<String>
    suspend fun loginIn(
        email: String,
        password: String,
    ): Result<String>

    suspend fun getCurrentUserRole(): String?

}
class AuthenticationRepositoryImpl(val firebaseService: FirebaseService): AuthenticationRepository {
    override suspend fun signUp(
        email: String,
        password: String,
        role: String
    ): Result<String> {
      return  firebaseService.signUp(email, password, role)
    }

    override suspend fun loginIn(
        email: String,
        password: String
    ): Result<String> {
      return  firebaseService.signIn(email, password)
    }

    override suspend fun getCurrentUserRole(): String? {
   return  firebaseService.getCurrentUserRole()
    }

}