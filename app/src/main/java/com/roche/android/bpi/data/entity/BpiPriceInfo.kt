package com.roche.android.bpi.data.entity

data class BpiPriceInfo(
    val time: BpiTimeInfo,
    val disclaimer: String,
    val chartName: String,
    val bpi: HashMap<String, BpiCurrency>
)
