package com.example.patientdatamanagementsystem.data.appRepo

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppPreferencesRepo(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(
        "app_preferences"
    )

    companion object {
        val USER_ROLE = stringPreferencesKey("user_role")

    }

    val role: Flow<String> = context.dataStore.data.map {
        it[USER_ROLE] ?: ""
    }

    suspend fun saveRoleLocal(role: String) {
        context.dataStore.edit {
            it[USER_ROLE] = role
        }
    }


}