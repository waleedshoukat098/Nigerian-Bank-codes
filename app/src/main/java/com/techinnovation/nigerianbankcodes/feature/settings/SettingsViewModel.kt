package com.techinnovation.nigerianbankcodes.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techinnovation.nigerianbankcodes.core.domain.repository.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    val state: StateFlow<SettingsUiState> = combine(
        preferencesRepository.darkModeEnabled,
        preferencesRepository.autoSaveEnabled,
        preferencesRepository.vibrationEnabled,
        preferencesRepository.premiumEnabled
    ) { dark, autoSave, vibration, premium ->
        SettingsUiState(dark, autoSave, vibration, premium)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SettingsUiState())

    fun setDarkMode(enabled: Boolean) = viewModelScope.launch { preferencesRepository.setDarkMode(enabled) }
    fun setAutoSave(enabled: Boolean) = viewModelScope.launch { preferencesRepository.setAutoSave(enabled) }
    fun setVibration(enabled: Boolean) = viewModelScope.launch { preferencesRepository.setVibration(enabled) }
}

data class SettingsUiState(
    val darkMode: Boolean = false,
    val autoSave: Boolean = true,
    val vibration: Boolean = true,
    val premiumEnabled: Boolean = false
)
