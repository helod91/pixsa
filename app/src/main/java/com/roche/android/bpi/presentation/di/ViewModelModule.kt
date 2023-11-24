package com.roche.android.bpi.presentation.di

import com.roche.android.bpi.presentation.features.currencies.CurrenciesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { CurrenciesViewModel(get()) }
}