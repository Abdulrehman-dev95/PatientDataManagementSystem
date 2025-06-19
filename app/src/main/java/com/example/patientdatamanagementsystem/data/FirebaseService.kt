package com.example.patientdatamanagementsystem.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


class FirebaseService(
    private val auth: FirebaseAuth, private val db: FirebaseFirestore
) {

    companion object {
        fun getFirebaseInstance(
            auth: FirebaseAuth, db: FirebaseFirestore
        ): FirebaseService {
            return FirebaseService(auth, db)
        }

    }

    suspend fun signIn(
        email: String, password: String
    ): Result<String> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val userId = authResult.user?.uid ?: return Result.failure(Exception("User not found"))
            val role = getUserRole(userId)

            Result.success(role)
        } catch (
            e: Exception
        ){
            Result.failure(e)
        }

    }


    suspend fun signUp(
        email: String, password: String, role: String
    ): Result<String> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(
                email, password
            ).await()
            val userId = authResult.user?.uid ?: return Result.failure(Exception("User not found"))
            saveUserRole(userId, role)
            Result.success(role)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    suspend fun getCurrentUserRole(): String? {
        val userId = auth.currentUser?.uid ?: return null
        return try {
            getUserRole(userId)
        } catch (
            e: Exception
        ) {
            "Current User Role Not Found error${e.message}"
        }


    }

private suspend fun saveUserRole(
        userId: String, role: String
    ) {

        db.collection(
            "user_roles"
        ).document(
            userId
        ).set(
            mapOf(
                "role" to role
            )
        ).await()

    }

  private suspend fun getUserRole(userId: String): String {
        val document = db.collection("user_roles").document(userId).get().await()
        return document.getString("role") ?: throw Exception("Role not Found")
    }


}
