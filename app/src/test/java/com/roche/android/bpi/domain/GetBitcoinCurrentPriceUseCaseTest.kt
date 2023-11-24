package com.roche.android.bpi.domain

import com.roche.android.bpi.domain.entity.BitcoinCurrencyResult
import com.roche.android.bpi.domain.entity.Data
import com.roche.android.bpi.domain.repository.BpiRepository
import com.roche.android.bpi.domain.usecase.GetBitcoinCurrentPriceUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.HttpException

class GetBitcoinCurrentPriceUseCaseTest {

    private lateinit var bpiRepository: BpiRepository
    private lateinit var getBitcoinCurrentPriceUseCase: GetBitcoinCurrentPriceUseCase

    @Before
    fun before() {
        bpiRepository = mock()
        getBitcoinCurrentPriceUseCase = GetBitcoinCurrentPriceUseCase(bpiRepository)
    }

    @Test
    fun `getBitcoinCurrentPriceUseCase returns error result on error`() {
        runBlocking {
            val exception: HttpException = mock()
            val mockedResult = Data.error<BitcoinCurrencyResult>(exception)
            whenever(bpiRepository.getBitcoinCurrentPrice()).thenReturn(mockedResult)

            val result = getBitcoinCurrentPriceUseCase()

            Assert.assertEquals(result, mockedResult)
        }
    }

    @Test
    fun `getBitcoinCurrentPriceUseCase returns success result with price info on success`() {
        runBlocking {
            val currencyMap: BitcoinCurrencyResult = mock()
            val mockedResult = Data.success(currencyMap)
            whenever(bpiRepository.getBitcoinCurrentPrice()).thenReturn(mockedResult)

            val result = getBitcoinCurrentPriceUseCase()

            Assert.assertEquals(result, mockedResult)
            Assert.assertEquals(result.result, mockedResult.result)
        }
    }
}