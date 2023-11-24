package com.roche.android.bpi.domain.usecase

import com.roche.android.bpi.domain.entity.BitcoinCurrencyResult
import com.roche.android.bpi.domain.entity.Data
import com.roche.android.bpi.domain.repository.BpiRepository

class GetBitcoinCurrentPriceUseCase(private val repository: BpiRepository) {
    suspend operator fun invoke(): Data<BitcoinCurrencyResult> =
        repository.getBitcoinCurrentPrice()
}