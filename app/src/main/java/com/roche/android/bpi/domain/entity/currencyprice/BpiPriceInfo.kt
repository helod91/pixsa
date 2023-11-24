package com.roche.android.bpi.domain.entity.currencyprice

data class BpiPriceInfo(
    val time: BpiTimeInfo,
    val disclaimer: String,
    val chartName: String,
    val bpi: BpiCurrencies
)
