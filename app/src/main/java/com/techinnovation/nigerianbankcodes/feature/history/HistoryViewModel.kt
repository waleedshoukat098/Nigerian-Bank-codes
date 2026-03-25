package com.techinnovation.nigerianbankcodes.feature.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techinnovation.nigerianbankcodes.core.domain.model.HistoryType
import com.techinnovation.nigerianbankcodes.core.domain.model.ScanRecord
import com.techinnovation.nigerianbankcodes.core.domain.usecase.ClearHistoryUseCase
import com.techinnovation.nigerianbankcodes.core.domain.usecase.DeleteHistoryItemUseCase
import com.techinnovation.nigerianbankcodes.core.domain.usecase.ObserveHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

enum class HistoryFilter { All, QR, OCR, CONVERSION }

data class HistoryItemUi(
    val id: Long,
    val title: String,
    val sourceLabel: String,
    val contentPreview: String,
    val dateLabel: String,
    val type: HistoryType
)

@HiltViewModel
class HistoryViewModel @Inject constructor(
    observeHistoryUseCase: ObserveHistoryUseCase,
    private val deleteHistoryItemUseCase: DeleteHistoryItemUseCase,
    private val clearHistoryUseCase: ClearHistoryUseCase
) : ViewModel() {

    private val query = MutableStateFlow("")
    private val filter = MutableStateFlow(HistoryFilter.All)
    private val _events = MutableSharedFlow<String>()
    val events = _events.asSharedFlow()

    val state: StateFlow<HistoryUiState> = combine(
        observeHistoryUseCase(),
        query,
        filter
    ) { history, q, f ->
        val filtered = history
            .filter { record ->
                val matchesType = f == HistoryFilter.All || record.type.name == f.name
                val matchesQuery = q.isBlank() || record.title.contains(q, true) || record.extractedText.contains(q, true)
                matchesType && matchesQuery
            }
            .map(::toUi)

        HistoryUiState(filtered, q, f)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), HistoryUiState())

    fun updateQuery(value: String) = query.update { value }
    fun setFilter(newFilter: HistoryFilter) = filter.update { newFilter }

    fun deleteItem(id: Long) {
        viewModelScope.launch {
            deleteHistoryItemUseCase(id)
            _events.emit("Item deleted")
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            clearHistoryUseCase()
            _events.emit("History cleared")
        }
    }

    private fun toUi(record: ScanRecord): HistoryItemUi {
        val formatter = DateTimeFormatter.ofPattern("MMM d, HH:mm").withZone(ZoneId.systemDefault())
        return HistoryItemUi(
            id = record.id,
            title = record.title,
            sourceLabel = record.sourceLabel,
            contentPreview = record.extractedText.take(100),
            dateLabel = formatter.format(record.createdAt),
            type = record.type
        )
    }
}

data class HistoryUiState(
    val items: List<HistoryItemUi> = emptyList(),
    val query: String = "",
    val filter: HistoryFilter = HistoryFilter.All
)
