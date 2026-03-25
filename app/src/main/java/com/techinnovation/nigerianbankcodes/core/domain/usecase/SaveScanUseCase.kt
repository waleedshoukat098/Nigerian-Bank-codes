package com.techinnovation.nigerianbankcodes.core.domain.usecase

import com.techinnovation.nigerianbankcodes.core.domain.model.HistoryType
import com.techinnovation.nigerianbankcodes.core.domain.repository.ScanRepository
import javax.inject.Inject

class SaveScanUseCase @Inject constructor(
    private val repository: ScanRepository
) {
    suspend operator fun invoke(type: HistoryType, title: String, sourceLabel: String, text: String) {
        repository.saveRecord(type, title, sourceLabel, text)
    }
}
