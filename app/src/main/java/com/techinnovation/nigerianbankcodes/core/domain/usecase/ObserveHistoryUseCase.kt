package com.techinnovation.nigerianbankcodes.core.domain.usecase

import com.techinnovation.nigerianbankcodes.core.domain.repository.ScanRepository
import javax.inject.Inject

class ObserveHistoryUseCase @Inject constructor(
    private val repository: ScanRepository
) {
    operator fun invoke() = repository.observeHistory()
}
