package com.roche.android.bpi.domain.usecase

import com.roche.android.bpi.domain.entity.BitcoinCurrency
import com.roche.android.bpi.domain.entity.Data
import com.roche.android.bpi.domain.repository.BpiRepository

class GetBitcoinCurrentPriceUseCase(private val repository: BpiRepository) {
    suspend fun execute(): Data<HashMap<String, BitcoinCurrency>> =
        repository.getBitcoinCurrentPrice()
}