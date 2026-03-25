package com.techinnovation.nigerianbankcodes.core.data.repository

import com.techinnovation.nigerianbankcodes.core.data.local.ScanDao
import com.techinnovation.nigerianbankcodes.core.data.local.ScanEntity
import com.techinnovation.nigerianbankcodes.core.domain.model.HistoryType
import com.techinnovation.nigerianbankcodes.core.domain.model.ScanRecord
import com.techinnovation.nigerianbankcodes.core.domain.repository.ScanRepository
import kotlinx.coroutines.flow.map
import java.time.Instant
import javax.inject.Inject

class ScanRepositoryImpl @Inject constructor(
    private val scanDao: ScanDao
) : ScanRepository {

    override fun observeHistory() = scanDao.observeHistory().map { entities ->
        entities.map { entity ->
            ScanRecord(
                id = entity.id,
                type = HistoryType.valueOf(entity.type),
                title = entity.title,
                sourceLabel = entity.sourceLabel,
                extractedText = entity.extractedText,
                createdAt = Instant.ofEpochMilli(entity.createdAtEpochMs),
                isFavorite = entity.isFavorite
            )
        }
    }

    override suspend fun saveRecord(type: HistoryType, title: String, sourceLabel: String, text: String) {
        scanDao.insert(
            ScanEntity(
                type = type.name,
                title = title,
                sourceLabel = sourceLabel,
                extractedText = text,
                createdAtEpochMs = System.currentTimeMillis()
            )
        )
    }

    override suspend fun deleteScan(id: Long) = scanDao.deleteById(id)

    override suspend fun clearAll() = scanDao.clearAll()
}
