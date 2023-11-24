package com.roche.android.bpi.data.di

import com.roche.android.bpi.data.mapper.BitcoinCurrencyMapper
import org.koin.dsl.module

val mapperModules = module {
    factory {
        BitcoinCurrencyMapper()
    }
}