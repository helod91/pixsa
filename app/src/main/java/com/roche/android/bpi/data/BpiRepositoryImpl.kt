package com.roche.android.bpi.data

import com.google.gson.Gson
import com.roche.android.bpi.domain.entity.BpiPriceInfo
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.gildor.coroutines.okhttp.await
import java.io.IOException

class BpiRepositoryImpl: BpiRepository {
    override suspend fun getBitcoinCurrentPrice(): BpiPriceInfo {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.coindesk.com/v1/bpi/currentprice.json")
            .build()
        val gson = Gson()

        val response = client.newCall(request).await()
        if (!response.isSuccessful) throw IOException("Unexpected code $response")

        return gson.fromJson(response.body?.charStream(), BpiPriceInfo::class.java)
    }
}