package com.binus.core.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.binus.core.domain.preferences.Preferences
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = Preferences.DATA_STORE_NAME)

@Singleton
class DefaultPreferences @Inject constructor(
    @ApplicationContext private val context: Context
): Preferences {
    private val loginKey = booleanPreferencesKey(Preferences.IS_LOGIN)

    override suspend fun saveLogin(isLogin: Boolean) {
        context.dataStore.edit { settings ->
            settings[loginKey] = isLogin
        }
    }

    override fun loadIsLogin(): Flow<Boolean> {
        return context.dataStore.data
            .map { preferences ->
                preferences[loginKey] ?: false
            }
    }
}