package com.example.kotlin.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreFriends(private val context: Context) {

    // to make sure there is only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferenceDataStore("friend")
        val FRIEND_KEY = stringPreferenceKey("friend_key")
    }

    // to get the friend
    val getFriend: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[FRIEND_KEY] ?: ""
        }

    // to save the friend
    suspend fun saveFriend(name: String) {
        context.dataStore.edit { preferences ->
            preferences[FRIEND_KEY] = name
        }
    }
}