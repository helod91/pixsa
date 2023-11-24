package com.roche.android.bpi.domain.entity

data class BpiPriceInfo(
    val time: BpiTimeInfo,
    val disclaimer: String,
    val chartName: String,
    val bpi: BpiCurrencies
)
