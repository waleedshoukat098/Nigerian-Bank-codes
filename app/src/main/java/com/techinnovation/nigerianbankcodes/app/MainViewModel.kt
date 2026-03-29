package com.techinnovation.nigerianbankcodes.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techinnovation.nigerianbankcodes.core.domain.repository.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    val darkMode = preferencesRepository.darkModeEnabled.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )

    val onboardingCompleted = preferencesRepository.hasCompletedOnboarding.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )

    fun completeOnboarding() {
        viewModelScope.launch { preferencesRepository.setOnboardingComplete(true) }
    }
}
