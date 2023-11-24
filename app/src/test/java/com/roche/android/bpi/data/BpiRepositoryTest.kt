package com.roche.android.bpi.data

import com.roche.android.bpi.data.apiservice.BpiApiService
import com.roche.android.bpi.domain.entity.currencyprice.BpiPriceInfo
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.junit.Assert.assertEquals
import retrofit2.HttpException

class BpiRepositoryTest {

    private lateinit var bpiApiService: BpiApiService
    private lateinit var bpiRepository: BpiRepository

    @Before
    fun before() {
        bpiApiService = mock()
        bpiRepository = BpiRepositoryImpl(bpiApiService)
    }

    @Test
    fun `getBitcoinCurrentPrices API service exception results in an error result`() {
        runBlocking {
            val exception: HttpException = mock()
            whenever(bpiApiService.getBitcoinCurrentPrices()).thenThrow(exception)

            val result = bpiRepository.getBitcoinCurrentPrice()

            assertEquals(result.error, exception)
        }
    }

    @Test
    fun `getBitcoinCurrentPrices API service success results in an success result with priceInfo`() {
        runBlocking {
            val priceInfo: BpiPriceInfo = mock()
            whenever(bpiApiService.getBitcoinCurrentPrices()).thenReturn(priceInfo)

            val result = bpiRepository.getBitcoinCurrentPrice()

            assertEquals(result.result, priceInfo)
        }
    }
}