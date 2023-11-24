package com.roche.android.bpi.domain.di

import com.roche.android.bpi.domain.usecase.GetBitcoinCurrentPriceUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetBitcoinCurrentPriceUseCase(get()) }
}