package com.roche.android.bpi.data.datasource

import com.roche.android.bpi.data.apiservice.BpiApiService
import com.roche.android.bpi.data.entity.BpiPriceInfo

class BpiPriceInfoDataSource(
    private val bpiApiService: BpiApiService
) {

    suspend fun getBitcoinCurrentPrices(): BpiPriceInfo =
        bpiApiService.getBitcoinCurrentPrices()
}