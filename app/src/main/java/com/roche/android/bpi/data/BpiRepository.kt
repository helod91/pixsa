package com.roche.android.bpi.data

import com.roche.android.bpi.domain.entity.Data
import com.roche.android.bpi.domain.entity.currencyprice.BpiPriceInfo

interface BpiRepository {
    suspend fun getBitcoinCurrentPrice(): Data<BpiPriceInfo>
}