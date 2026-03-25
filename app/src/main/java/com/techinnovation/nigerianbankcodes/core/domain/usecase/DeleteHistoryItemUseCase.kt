package com.techinnovation.nigerianbankcodes.core.domain.usecase

import com.techinnovation.nigerianbankcodes.core.domain.repository.ScanRepository
import javax.inject.Inject

class DeleteHistoryItemUseCase @Inject constructor(
    private val repository: ScanRepository
) {
    suspend operator fun invoke(id: Long) = repository.deleteScan(id)
}
