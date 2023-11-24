package com.roche.android.bpi.domain.entity

import java.util.Date

data class BitcoinCurrencyResult(
    val currencies: List<BitcoinCurrency>,
    val updated: Date
)

data class BitcoinCurrency(
    val rate: String,
    val symbol: String,
    val currency: String
)