package com.roche.android.bpi.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.roche.android.bpi.data.apiservice.GsonDateDeSerializer
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date

private const val BASE_URL = "https://api.coindesk.com/"

val networkModules = module {

    single {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.build()
    }

    single<Gson> {
        GsonBuilder()
            .registerTypeAdapter(Date::class.java, GsonDateDeSerializer())
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
}