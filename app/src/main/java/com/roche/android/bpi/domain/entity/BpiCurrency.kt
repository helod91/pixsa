package com.roche.android.bpi.domain.entity

data class BpiCurrency(
    val code: String,
    val symbol: String,
    val rate: String,
    val description: String,
    val rate_float: Float
)
