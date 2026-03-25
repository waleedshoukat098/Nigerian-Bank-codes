package com.techinnovation.nigerianbankcodes.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.techinnovation.nigerianbankcodes.core.domain.repository.PreferencesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "smart_scanner_prefs")

@Singleton
class PreferencesDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) : PreferencesRepository {

    override val darkModeEnabled: Flow<Boolean> = context.dataStore.data.map { it[DARK_MODE_KEY] ?: false }
    override val hasCompletedOnboarding: Flow<Boolean> = context.dataStore.data.map { it[ONBOARDING_KEY] ?: false }
    override val autoSaveEnabled: Flow<Boolean> = context.dataStore.data.map { it[AUTO_SAVE_KEY] ?: true }
    override val vibrationEnabled: Flow<Boolean> = context.dataStore.data.map { it[VIBRATION_KEY] ?: true }
    override val premiumEnabled: Flow<Boolean> = context.dataStore.data.map { it[PREMIUM_KEY] ?: false }

    override suspend fun setDarkMode(enabled: Boolean) {
        context.dataStore.edit { it[DARK_MODE_KEY] = enabled }
    }

    override suspend fun setOnboardingComplete(completed: Boolean) {
        context.dataStore.edit { it[ONBOARDING_KEY] = completed }
    }

    override suspend fun setAutoSave(enabled: Boolean) {
        context.dataStore.edit { it[AUTO_SAVE_KEY] = enabled }
    }

    override suspend fun setVibration(enabled: Boolean) {
        context.dataStore.edit { it[VIBRATION_KEY] = enabled }
    }

    override suspend fun setPremiumEnabled(enabled: Boolean) {
        context.dataStore.edit { it[PREMIUM_KEY] = enabled }
    }

    private companion object {
        val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
        val ONBOARDING_KEY = booleanPreferencesKey("onboarding_complete")
        val AUTO_SAVE_KEY = booleanPreferencesKey("auto_save")
        val VIBRATION_KEY = booleanPreferencesKey("vibration")
        val PREMIUM_KEY = booleanPreferencesKey("premium_enabled")
    }
}
