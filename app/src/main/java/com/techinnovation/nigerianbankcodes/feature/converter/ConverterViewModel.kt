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

    fun selectTool(tool: ConverterTool) = baseState.update {
        it.copy(
            selectedTool = tool,
            inputA = "",
            inputB = defaultSecondaryInput(tool),
            result = "",
            error = null
        )
    }
    fun updateInputA(value: String) = baseState.update { it.copy(inputA = value, error = null) }
    fun updateInputB(value: String) = baseState.update { it.copy(inputB = value, error = null) }

    fun calculate() {
        val s = state.value
        val result = runCatching {
            when (s.selectedTool) {
                ConverterTool.PdfMerge -> {
                    val count = s.inputA.toInt()
                    require(count >= 2) { "Add at least 2 PDF files" }
                    "Ready to merge $count PDFs into a single document."
                }
                ConverterTool.PdfConvert -> {
                    val count = s.inputA.toInt()
                    require(count > 0) { "Enter number of images" }
                    val imageSizeMb = s.inputB.toDoubleOrNull() ?: 1.5
                    require(imageSizeMb > 0) { "Average image size must be > 0" }
                    val totalInput = count * imageSizeMb
                    val estimatedPdfSize = totalInput * 0.78
                    "Converted $count images (~${"%.2f".format(totalInput)} MB) to PDF (~${"%.2f".format(estimatedPdfSize)} MB)."
                }
                ConverterTool.Currency -> {
                    val amount = s.inputA.toDouble()
                    val pair = (s.inputB.ifBlank { "USD-NGN" }).uppercase()
                    val parts = pair.split("-", "/").map { it.trim() }.filter { it.isNotBlank() }
                    require(parts.size == 2) { "Use pair format like USD-NGN" }
                    val from = parts[0]
                    val to = parts[1]
                    val fromRate = ratesToUsd[from] ?: error("Unsupported base currency: $from")
                    val toRate = ratesToUsd[to] ?: error("Unsupported target currency: $to")
                    val usdValue = amount * fromRate
                    val converted = usdValue / toRate
                    "${"%.2f".format(amount)} $from = ${"%.2f".format(converted)} $to"
                }
                ConverterTool.DocReader -> {
                    val text = s.inputA.trim()
                    require(text.isNotBlank()) { "Paste document text first" }
                    val words = text.split("\\s+".toRegex()).filter { it.isNotBlank() }.size
                    val readingMinutes = (words / 200.0).coerceAtLeast(0.1)
                    val preview = text.lines().firstOrNull()?.take(80).orEmpty()
                    "Preview: \"$preview\" • Words: $words • Read time: ${"%.1f".format(readingMinutes)} min"
                }
                ConverterTool.WordCount -> {
                    val text = s.inputA.trim()
                    require(text.isNotBlank()) { "Enter text first" }
                    val words = text.split("\\s+".toRegex()).filter { it.isNotBlank() }.size
                    val chars = text.length
                    val lines = text.lines().size
                    "Words: $words • Chars: $chars • Lines: $lines"
                }
                ConverterTool.ImageCompress -> {
                    val mb = s.inputA.toDouble()
                    require(mb > 0) { "Enter a valid image size in MB" }
                    val quality = (s.inputB.toDoubleOrNull() ?: 70.0).coerceIn(1.0, 100.0)
                    val reduced = mb * (quality / 100.0) * 0.85
                    val savings = mb - reduced
                    "Estimated: ${"%.2f".format(reduced)} MB (saved ${"%.2f".format(savings)} MB at ${quality.toInt()}% quality)"
                }
                ConverterTool.QR_GEN -> {
                    val content = s.inputA.trim()
                    require(content.isNotBlank()) { "Enter text or URL to encode" }
                    "QR content ready: $content"
                }
            }
        }.getOrElse { throwable ->
            baseState.update { current -> current.copy(error = throwable.message ?: "Invalid input. Please check your values.") }
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
                "${s.selectedTool.displayName} Result",
                "Tool",
                "${s.inputA} => ${s.result}"
            )
            _events.emit("Result saved to history")
        }
    }
}

enum class ConverterTool(val displayName: String) {
    Currency("Currency"),
    QR_GEN("QR Generator"),
    PdfMerge("PDF Merge"),
    PdfConvert("PDF Convert"),
    DocReader("Doc Reader"),
    ImageCompress("Image Compress"),
    WordCount("Word Count")
}

data class ConverterUiState(
    val selectedTool: ConverterTool = ConverterTool.Currency,
    val inputA: String = "",
    val inputB: String = "USD-NGN",
    val result: String = "",
    val error: String? = null,
    val isPremium: Boolean = false
)

private fun defaultSecondaryInput(tool: ConverterTool): String = when (tool) {
    ConverterTool.Currency -> "USD-NGN"
    ConverterTool.ImageCompress -> "70"
    ConverterTool.PdfConvert -> "1.5"
    else -> ""
}

private val ratesToUsd = mapOf(
    "USD" to 1.0,
    "NGN" to 0.00064,
    "EUR" to 1.08,
    "GBP" to 1.27,
    "JPY" to 0.0067,
    "CAD" to 0.74,
    "AUD" to 0.66,
    "INR" to 0.012,
    "KES" to 0.0078,
    "GHS" to 0.076
)
