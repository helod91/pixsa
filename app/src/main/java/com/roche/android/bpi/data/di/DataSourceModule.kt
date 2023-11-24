package com.roche.android.bpi.data.di

import com.roche.android.bpi.data.datasource.BpiPriceInfoDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    factory {
        BpiPriceInfoDataSource(get())
    }
}