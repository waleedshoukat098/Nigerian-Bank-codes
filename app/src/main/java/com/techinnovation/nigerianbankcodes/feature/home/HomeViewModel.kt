package com.techinnovation.nigerianbankcodes.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techinnovation.nigerianbankcodes.core.domain.repository.PreferencesRepository
import com.techinnovation.nigerianbankcodes.core.domain.usecase.ObserveHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    observeHistoryUseCase: ObserveHistoryUseCase,
    preferencesRepository: PreferencesRepository
) : ViewModel() {

    val state: StateFlow<HomeUiState> = combine(
        observeHistoryUseCase(),
        preferencesRepository.premiumEnabled
    ) { records, premium ->
        HomeUiState(
            isPremium = premium,
            recentItems = records.take(3).map { HomeRecentItem(it.title, it.extractedText.take(60)) }
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), HomeUiState())
}

data class HomeUiState(
    val isPremium: Boolean = false,
    val recentItems: List<HomeRecentItem> = emptyList()
)

data class HomeRecentItem(
    val title: String,
    val preview: String
)
