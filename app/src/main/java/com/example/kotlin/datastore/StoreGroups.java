package com.example.kotlin.datastore;

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreGroups(private val context: Context) {

    // to make sure there is only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferenceDataStore("group")
        val GROUP_KEY = stringPreferenceKey("group_key")
    }

    // to get the group
    val getGroup: Flow<String?> = context.dataStore.data
            .map { preferences ->
            preferences[GROUP_KEY] ?: ""
    }

    // to save the Group
    suspend fun saveGroup(name: String) {
        context.dataStore.edit { preferences ->
                preferences[GROUP_KEY] = name
        }
    }
}
