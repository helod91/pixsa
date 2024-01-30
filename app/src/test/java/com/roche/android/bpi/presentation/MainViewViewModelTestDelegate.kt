package com.roche.android.bpi.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.roche.android.bpi.domain.entity.BitcoinCurrencyResult
import com.roche.android.bpi.domain.entity.Data
import com.roche.android.bpi.domain.usecase.GetBitcoinCurrentPriceUseCase
import com.roche.android.bpi.presentation.features.currencies.CurrenciesState
import com.roche.android.bpi.presentation.features.currencies.CurrenciesViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import retrofit2.HttpException

class MainViewViewModelTestDelegate {

    private lateinit var getBitcoinCurrentPriceUseCase: GetBitcoinCurrentPriceUseCase
    private lateinit var viewModel: CurrenciesViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        getBitcoinCurrentPriceUseCase = mock()
        viewModel = CurrenciesViewModel(getBitcoinCurrentPriceUseCase)
    }

    @Test
    fun `Should emit error result when use case results an error`() {
        runBlocking {
            val exception: HttpException = mock()
            val errorResult = Data.error<BitcoinCurrencyResult>(exception)

            whenever(getBitcoinCurrentPriceUseCase()).thenReturn(errorResult)

            val emittedValues = mutableListOf<CurrenciesState>()
            val job = launch {
                viewModel.state.toList(emittedValues)
            }

            viewModel.loadCurrentPrices()

            delay(200)

            Assert.assertTrue(emittedValues[0].loading)
            Assert.assertEquals(emittedValues[1].error, exception)
            job.cancel()
        }
    }

    @Test
    fun `Should emit success result when use case results a success`() {
        runBlocking {
            val successResult = Data.success<BitcoinCurrencyResult>(mock())

            whenever(getBitcoinCurrentPriceUseCase()).thenReturn(successResult)

            val emittedValues = mutableListOf<CurrenciesState>()
            val job = launch {
                viewModel.state.toList(emittedValues)
            }

            viewModel.loadCurrentPrices()

            delay(200)

            Assert.assertTrue(emittedValues[0].loading)
            Assert.assertNotNull(emittedValues[1].currencies)

            job.cancel()
        }
    }
}