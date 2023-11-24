package com.roche.android.bpi.data.mapper

import com.roche.android.bpi.data.entity.BpiPriceInfo
import com.roche.android.bpi.domain.entity.BitcoinCurrency

class BitcoinCurrencyMapper {

    fun toBitcoinCurrencyMap(bpiPriceInfo: BpiPriceInfo): HashMap<String, BitcoinCurrency> {
        val bitcoinCurrencyMap = HashMap<String, BitcoinCurrency>()
        bpiPriceInfo.bpi.map { entry ->
            bitcoinCurrencyMap[entry.key] = BitcoinCurrency(
                entry.value.rate,
                entry.value.symbol
            )
        }

        return bitcoinCurrencyMap
    }
}