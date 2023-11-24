package com.roche.android.bpi.data.di

import com.roche.android.bpi.data.apiservice.BpiApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModules = module {
    single<BpiApiService> {
        get<Retrofit>().create(BpiApiService::class.java)
    }
}