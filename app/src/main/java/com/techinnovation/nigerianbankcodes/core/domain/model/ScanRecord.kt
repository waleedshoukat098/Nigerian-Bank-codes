package com.techinnovation.nigerianbankcodes.core.domain.model

import java.time.Instant

data class ScanRecord(
    val id: Long,
    val type: HistoryType,
    val title: String,
    val sourceLabel: String,
    val extractedText: String,
    val createdAt: Instant,
    val isFavorite: Boolean = false
)
