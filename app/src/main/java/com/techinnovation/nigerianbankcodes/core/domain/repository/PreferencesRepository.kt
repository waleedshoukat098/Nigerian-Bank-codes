package com.techinnovation.nigerianbankcodes.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    val darkModeEnabled: Flow<Boolean>
    val hasCompletedOnboarding: Flow<Boolean>
    val autoSaveEnabled: Flow<Boolean>
    val vibrationEnabled: Flow<Boolean>
    val premiumEnabled: Flow<Boolean>
    suspend fun setDarkMode(enabled: Boolean)
    suspend fun setOnboardingComplete(completed: Boolean)
    suspend fun setAutoSave(enabled: Boolean)
    suspend fun setVibration(enabled: Boolean)
    suspend fun setPremiumEnabled(enabled: Boolean)
}
