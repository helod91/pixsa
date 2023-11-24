package com.roche.android.bpi.domain.repository

import com.roche.android.bpi.domain.entity.BitcoinCurrency
import com.roche.android.bpi.domain.entity.Data

interface BpiRepository {
    suspend fun getBitcoinCurrentPrice(): Data<HashMap<String, BitcoinCurrency>>
}