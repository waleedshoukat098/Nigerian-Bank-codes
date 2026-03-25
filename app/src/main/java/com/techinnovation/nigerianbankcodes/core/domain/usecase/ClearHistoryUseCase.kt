package com.techinnovation.nigerianbankcodes.core.domain.usecase

import com.techinnovation.nigerianbankcodes.core.domain.repository.ScanRepository
import javax.inject.Inject

class ClearHistoryUseCase @Inject constructor(
    private val repository: ScanRepository
) {
    suspend operator fun invoke() = repository.clearAll()
}
