package com.techinnovation.nigerianbankcodes.core.domain.repository

import com.techinnovation.nigerianbankcodes.core.domain.model.HistoryType
import com.techinnovation.nigerianbankcodes.core.domain.model.ScanRecord
import kotlinx.coroutines.flow.Flow

interface ScanRepository {
    fun observeHistory(): Flow<List<ScanRecord>>
    suspend fun saveRecord(type: HistoryType, title: String, sourceLabel: String, text: String)
    suspend fun deleteScan(id: Long)
    suspend fun clearAll()
}
