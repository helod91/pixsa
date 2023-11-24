package com.roche.android.bpi.domain.repository

import com.roche.android.bpi.domain.entity.BitcoinCurrencyResult
import com.roche.android.bpi.domain.entity.Data

interface BpiRepository {
    suspend fun getBitcoinCurrentPrice(): Data<BitcoinCurrencyResult>
}