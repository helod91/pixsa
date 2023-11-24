package com.roche.android.bpi.data.apiservice

import com.roche.android.bpi.domain.entity.currencyprice.BpiPriceInfo
import retrofit2.http.GET

interface BpiApiService {

    @GET("/v1/bpi/currentprice.json")
    suspend fun getBitcoinCurrentPrices(): BpiPriceInfo
}