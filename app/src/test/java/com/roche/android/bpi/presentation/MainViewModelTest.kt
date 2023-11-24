package com.roche.android.bpi.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.roche.android.bpi.domain.entity.Data
import com.roche.android.bpi.domain.entity.currencyprice.BpiPriceInfo
import com.roche.android.bpi.domain.usecase.GetBitcoinCurrentPriceUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import retrofit2.HttpException

class MainViewModelTest {

    private lateinit var getBitcoinCurrentPriceUseCase: GetBitcoinCurrentPriceUseCase
    private lateinit var mainViewModel: MainViewModel

    private lateinit var results: MutableList<Data<BpiPriceInfo>>

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        getBitcoinCurrentPriceUseCase = mock()
        results = mutableListOf()
        mainViewModel = MainViewModel(getBitcoinCurrentPriceUseCase)
        mainViewModel.bpiData.observeForever {
            results.add(it)
        }
    }

    @Test
    fun `Should emit error result when use case results an error`() {
        runBlocking {
            val exception: HttpException = mock()
            val errorResult = Data.error<BpiPriceInfo>(exception)

            whenever(getBitcoinCurrentPriceUseCase.execute()).thenReturn(errorResult)

            mainViewModel.loadCurrentPrices()
            delay(200)

            Assert.assertEquals(results[0], errorResult)
        }
    }

    @Test
    fun `Should emit success result when use case results a success`() {
        runBlocking {
            val successResult = Data.success<BpiPriceInfo>(mock())

            whenever(getBitcoinCurrentPriceUseCase.execute()).thenReturn(successResult)

            mainViewModel.loadCurrentPrices()
            delay(200)

            Assert.assertEquals(results[0], successResult)
        }
    }
}