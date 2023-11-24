package com.roche.android.bpi

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.roche.android.bpi.data.BpiRepository
import com.roche.android.bpi.data.BpiRepositoryImpl
import com.roche.android.bpi.data.apiservice.BpiApiService
import com.roche.android.bpi.domain.usecase.GetBitcoinCurrentPriceUseCase
import com.roche.android.bpi.presentation.MainViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.coindesk.com/"

val mainModule = module {
    viewModel { MainViewModel(get()) }
    single {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.build()
    }

    single<Gson> {
        GsonBuilder()
            .setLenient()
            .create()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }
    single<BpiApiService> {
        get<Retrofit>().create(BpiApiService::class.java)
    }
    single<BpiRepository> { BpiRepositoryImpl(get()) }
    factory { GetBitcoinCurrentPriceUseCase(get()) }
}