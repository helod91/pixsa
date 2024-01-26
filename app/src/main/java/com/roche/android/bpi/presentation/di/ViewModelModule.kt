package com.roche.android.bpi.presentation.di

import com.roche.android.bpi.presentation.common.CommonNavigationEventProcessor
import com.roche.android.bpi.presentation.features.currencies.CurrenciesEventProcessor
import com.roche.android.bpi.presentation.features.currencies.CurrenciesReducer
import com.roche.android.bpi.presentation.features.currencies.CurrenciesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { CurrenciesViewModel(
        eventProcessors = listOf(
            CommonNavigationEventProcessor(),
            CurrenciesEventProcessor(get(), get())
        ),
        reducers = listOf(
            CurrenciesReducer()
        ),
        dispatcherProvider = get()
    ) }
}