package com.example.akilisawa.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    }

    suspend fun saveAccessToken(accessToken: String) {
        context.dataStore.edit { settings ->
            settings[ACCESS_TOKEN_KEY] = accessToken
        }
    }

    suspend fun saveRefreshToken(refreshToken: String) {
        context.dataStore.edit { settings ->
            settings[REFRESH_TOKEN_KEY] = refreshToken
        }
    }

    val accessTokenFlow: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[ACCESS_TOKEN_KEY] ?: ""
        }

    val refreshTokenFlow: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[REFRESH_TOKEN_KEY] ?: ""
        }
}