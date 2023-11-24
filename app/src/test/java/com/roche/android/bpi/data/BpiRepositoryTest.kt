package com.roche.android.bpi.data

import com.roche.android.bpi.data.datasource.BpiPriceInfoDataSource
import com.roche.android.bpi.data.entity.BpiPriceInfo
import com.roche.android.bpi.data.mapper.BitcoinCurrencyMapper
import com.roche.android.bpi.data.repository.BpiRepositoryImpl
import com.roche.android.bpi.domain.entity.BitcoinCurrencyResult
import com.roche.android.bpi.domain.repository.BpiRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.HttpException

class BpiRepositoryTest {

    private lateinit var bpiPriceInfoDataSource: BpiPriceInfoDataSource
    private lateinit var bitcoinCurrencyMapper: BitcoinCurrencyMapper
    private lateinit var bpiRepository: BpiRepository

    @Before
    fun before() {
        bpiPriceInfoDataSource = mock()
        bitcoinCurrencyMapper = mock()

        bpiRepository = BpiRepositoryImpl(bpiPriceInfoDataSource, bitcoinCurrencyMapper)
    }

    @Test
    fun `getBitcoinCurrentPrices API service exception results in an error result`() {
        runBlocking {
            val exception: HttpException = mock()
            whenever(bpiPriceInfoDataSource.getBitcoinCurrentPrices()).thenThrow(exception)

            val result = bpiRepository.getBitcoinCurrentPrice()

            assertEquals(result.error, exception)
        }
    }

    @Test
    fun `getBitcoinCurrentPrices API service success results in an success result with priceInfo`() {
        runBlocking {
            val priceInfo: BpiPriceInfo = mock()
            val mockedResult: BitcoinCurrencyResult = mock()
            whenever(bpiPriceInfoDataSource.getBitcoinCurrentPrices()).thenReturn(priceInfo)
            whenever(bitcoinCurrencyMapper.toBitcoinCurrencyResult(priceInfo)).thenReturn(
                mockedResult
            )

            val result = bpiRepository.getBitcoinCurrentPrice()

            assertEquals(result.result, mockedResult)
        }
    }
}