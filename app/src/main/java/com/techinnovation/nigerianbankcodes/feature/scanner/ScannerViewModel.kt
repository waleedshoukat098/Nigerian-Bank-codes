package com.techinnovation.nigerianbankcodes.feature.scanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techinnovation.nigerianbankcodes.core.domain.model.HistoryType
import com.techinnovation.nigerianbankcodes.core.domain.usecase.SaveScanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val saveScanUseCase: SaveScanUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ScannerUiState())
    val state: StateFlow<ScannerUiState> = _state.asStateFlow()

    private val _events = MutableSharedFlow<String>()
    val events = _events.asSharedFlow()

    fun onQrResultDetected(result: String) {
        if (result.isBlank()) return
        _state.update { it.copy(qrResult = result, error = null) }
    }

    fun onOcrExtracted(text: String) {
        _state.update { it.copy(ocrResult = text, isProcessing = false, error = null) }
    }

    fun setProcessing(processing: Boolean) {
        _state.update { it.copy(isProcessing = processing) }
    }

    fun onError(message: String) {
        _state.update { it.copy(error = message, isProcessing = false) }
    }

    fun emitInfo(message: String) {
        viewModelScope.launch { _events.emit(message) }
    }

    fun saveQrResult() {
        val qr = state.value.qrResult ?: run {
            emitInfo("No QR result to save yet")
            return
        }
        viewModelScope.launch {
            saveScanUseCase(HistoryType.QR, "QR/Barcode", "CameraX", qr)
            _events.emit("Scan saved")
        }
    }

    fun saveOcrResult() {
        val ocr = state.value.ocrResult
        if (ocr.isBlank()) {
            emitInfo("Extract text first")
            return
        }
        viewModelScope.launch {
            saveScanUseCase(HistoryType.OCR, "Document OCR", "ML Kit", ocr)
            _events.emit("OCR saved")
        }
    }
}
