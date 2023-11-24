package com.roche.android.bpi

import com.roche.android.bpi.data.BpiRepository
import com.roche.android.bpi.data.BpiRepositoryImpl
import com.roche.android.bpi.domain.usecase.GetBitcoinCurrentPriceUseCase
import com.roche.android.bpi.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainViewModel(get()) }
    single<BpiRepository> { BpiRepositoryImpl() }
    factory { GetBitcoinCurrentPriceUseCase(get()) }
}