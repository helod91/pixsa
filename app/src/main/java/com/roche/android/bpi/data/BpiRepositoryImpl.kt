package com.roche.android.bpi.data

import com.roche.android.bpi.data.apiservice.BpiApiService
import com.roche.android.bpi.domain.entity.Data
import com.roche.android.bpi.domain.entity.currencyprice.BpiPriceInfo

class BpiRepositoryImpl(
    private val bpiApiService: BpiApiService
): BpiRepository {

    override suspend fun getBitcoinCurrentPrice(): Data<BpiPriceInfo> {
        return try {
            Data.success(bpiApiService.getBitcoinCurrentPrices())
        } catch (e: Exception) {
            Data.error(e)
        }
    }
}