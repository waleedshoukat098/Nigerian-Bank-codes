package com.techinnovation.nigerianbankcodes.feature.converter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techinnovation.nigerianbankcodes.core.domain.model.HistoryType
import com.techinnovation.nigerianbankcodes.core.domain.repository.PreferencesRepository
import com.techinnovation.nigerianbankcodes.core.domain.usecase.SaveScanUseCase
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
import java.time.LocalDate
import java.time.Period
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val saveScanUseCase: SaveScanUseCase,
    preferencesRepository: PreferencesRepository
) : ViewModel() {

    private val baseState = MutableStateFlow(ConverterUiState())
    private val _events = MutableSharedFlow<String>()
    val events = _events.asSharedFlow()

    val state: StateFlow<ConverterUiState> = combine(
        baseState,
        preferencesRepository.premiumEnabled
    ) { state, premium -> state.copy(isPremium = premium) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ConverterUiState())

    fun selectTool(tool: ConverterTool) = baseState.update { it.copy(selectedTool = tool, result = "", error = null) }
    fun updateInputA(value: String) = baseState.update { it.copy(inputA = value, error = null) }
    fun updateInputB(value: String) = baseState.update { it.copy(inputB = value, error = null) }

    fun calculate() {
        val s = state.value
        val result = runCatching {
            when (s.selectedTool) {
                ConverterTool.Unit -> {
                    val meters = s.inputA.toDouble()
                    "${"%.2f".format(meters * 3.28084)} ft"
                }
                ConverterTool.Currency -> {
                    val usd = s.inputA.toDouble()
                    "₦ ${"%,.2f".format(usd * 1550.0)}"
                }
                ConverterTool.Percentage -> {
                    val percent = s.inputA.toDouble()
                    val base = s.inputB.toDouble()
                    "${"%.2f".format(base * (percent / 100.0))}"
                }
                ConverterTool.Age -> {
                    val year = s.inputA.toInt()
                    require(year in 1900..LocalDate.now().year) { "Enter a valid birth year" }
                    "${Period.between(LocalDate.of(year, 1, 1), LocalDate.now()).years} years"
                }
                ConverterTool.Storage -> {
                    val mb = s.inputA.toDouble()
                    "${"%.2f".format(mb / 1024.0)} GB"
                }
                ConverterTool.QR_GEN -> {
                    "Generated QR for: ${s.inputA}"
                }
            }
        }.getOrElse {
            baseState.update { it.copy(error = "Invalid input. Please check your values.") }
            return
        }

        baseState.update { it.copy(result = result, error = null) }
    }

    fun generateQR(content: String) {
        if (content.isBlank()) return
        baseState.update { it.copy(inputA = content, selectedTool = ConverterTool.QR_GEN) }
        calculate()
    }

    fun saveResult() {
        val s = state.value
        if (s.result.isBlank()) {
            viewModelScope.launch { _events.emit("Run conversion first") }
            return
        }
        viewModelScope.launch {
            saveScanUseCase(
                HistoryType.CONVERSION,
                "${s.selectedTool.name} Result",
                "Tool",
                "${s.inputA} => ${s.result}"
            )
            _events.emit("Result saved to history")
        }
    }
}

enum class ConverterTool { Unit, Currency, Percentage, Age, Storage, QR_GEN }

data class ConverterUiState(
    val selectedTool: ConverterTool = ConverterTool.Unit,
    val inputA: String = "",
    val inputB: String = "",
    val result: String = "",
    val error: String? = null,
    val isPremium: Boolean = false
)
