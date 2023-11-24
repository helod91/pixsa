package com.roche.android.bpi.domain.usecase

import com.roche.android.bpi.data.BpiRepository
import com.roche.android.bpi.domain.entity.Data
import com.roche.android.bpi.domain.entity.currencyprice.BpiPriceInfo

class GetBitcoinCurrentPriceUseCase(private val repository: BpiRepository) {
    suspend fun execute(): Data<BpiPriceInfo> = repository.getBitcoinCurrentPrice()
}