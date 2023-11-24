package com.roche.android.bpi.data

import com.roche.android.bpi.domain.entity.BpiPriceInfo

interface BpiRepository {
    suspend fun getBitcoinCurrentPrice(): BpiPriceInfo
}