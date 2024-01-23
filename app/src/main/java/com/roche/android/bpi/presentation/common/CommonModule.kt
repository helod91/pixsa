package com.roche.android.bpi.presentation.common

import com.roche.android.bpi.presentation.common.exception.DefaultExceptionHandler
import com.roche.android.bpi.presentation.common.exception.ExceptionHandler
import com.roche.android.bpi.presentation.common.dispatcher.DispatcherProvider
import com.roche.android.bpi.presentation.common.dispatcher.StandardDispatcherProvider
import org.koin.dsl.module

val commonModule = module {
    single<DispatcherProvider> { StandardDispatcherProvider() }
    single<ExceptionHandler> { DefaultExceptionHandler() }
}