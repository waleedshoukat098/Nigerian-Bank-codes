package com.techinnovation.nigerianbankcodes.feature.scanner

data class ScannerUiState(
    val qrResult: String? = null,
    val ocrResult: String = "",
    val error: String? = null,
    val isProcessing: Boolean = false,
    val selectedTab: Int = 1
)
