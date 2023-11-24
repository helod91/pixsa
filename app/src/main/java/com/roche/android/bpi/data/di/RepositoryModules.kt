package com.roche.android.bpi.data.di

import com.roche.android.bpi.data.repository.BpiRepositoryImpl
import com.roche.android.bpi.domain.repository.BpiRepository
import org.koin.dsl.module

val repositoryModules = module {
    single<BpiRepository> {
        BpiRepositoryImpl(get(), get())
    }
}