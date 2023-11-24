package com.roche.android.bpi.data.repository

import com.roche.android.bpi.data.datasource.BpiPriceInfoDataSource
import com.roche.android.bpi.data.mapper.BitcoinCurrencyMapper
import com.roche.android.bpi.domain.entity.BitcoinCurrencyResult
import com.roche.android.bpi.domain.entity.Data
import com.roche.android.bpi.domain.repository.BpiRepository

class BpiRepositoryImpl(
    private val bpiPriceInfoDataSource: BpiPriceInfoDataSource,
    private val bitcoinCurrencyMapper: BitcoinCurrencyMapper
) : BpiRepository {

    override suspend fun getBitcoinCurrentPrice(): Data<BitcoinCurrencyResult> {
        return try {
            val apiResult = bpiPriceInfoDataSource.getBitcoinCurrentPrices()
            Data.success(bitcoinCurrencyMapper.toBitcoinCurrencyResult(apiResult))
        } catch (e: Exception) {
            Data.error(e)
        }
    }
}