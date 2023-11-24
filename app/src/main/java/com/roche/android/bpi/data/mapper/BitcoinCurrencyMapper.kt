package com.roche.android.bpi.data.mapper

import com.roche.android.bpi.data.entity.BpiPriceInfo
import com.roche.android.bpi.domain.entity.BitcoinCurrency
import com.roche.android.bpi.domain.entity.BitcoinCurrencyResult

class BitcoinCurrencyMapper {

    fun toBitcoinCurrencyResult(bpiPriceInfo: BpiPriceInfo): BitcoinCurrencyResult {
        val bitcoinCurrencies = ArrayList<BitcoinCurrency>()
        bpiPriceInfo.bpi.map { entry ->
            bitcoinCurrencies.add(
                BitcoinCurrency(
                    entry.value.rate,
                    entry.value.symbol,
                    entry.key
                )
            )
        }

        return BitcoinCurrencyResult(
            bitcoinCurrencies,
            bpiPriceInfo.time.updated
        )
    }
}