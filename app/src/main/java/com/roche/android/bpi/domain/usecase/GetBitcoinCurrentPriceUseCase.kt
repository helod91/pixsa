package com.roche.android.bpi.domain.usecase

import com.roche.android.bpi.data.BpiRepository
import com.roche.android.bpi.domain.entity.BpiPriceInfo

class GetBitcoinCurrentPriceUseCase(private val repository: BpiRepository) {
    suspend fun execute(): BpiPriceInfo = repository.getBitcoinCurrentPrice()
}