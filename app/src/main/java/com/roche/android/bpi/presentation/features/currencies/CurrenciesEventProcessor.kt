package com.roche.android.bpi.presentation.features.currencies

import com.roche.android.bpi.domain.usecase.GetBitcoinCurrentPriceUseCase
import com.roche.android.bpi.presentation.common.arch.EventProcessor
import com.roche.android.bpi.presentation.common.arch.Mutation
import com.roche.android.bpi.presentation.common.arch.SideEffect
import com.roche.android.bpi.presentation.common.arch.ViewEvent
import com.roche.android.bpi.presentation.common.exception.ExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

class CurrenciesEventProcessor(
    private val getBitcoinCurrentPriceUseCase: GetBitcoinCurrentPriceUseCase,
    private val exceptionHandler: ExceptionHandler
) : EventProcessor {
    override fun invoke(event: ViewEvent): Flow<Pair<Mutation?, SideEffect?>> =
        flow {
            if (event is CurrenciesEvent) {
                when (event) {
                    is CurrenciesEvent.Refresh, CurrenciesEvent.Retry ->
                        loadCurrentPrices()
                }
            }
        }

    private suspend fun FlowCollector<Pair<Mutation?, SideEffect?>>.loadCurrentPrices() {
        emit(CurrenciesMutation.ShowLoadContent to null)

        val result = getBitcoinCurrentPriceUseCase()
        if (result.result != null) {
            emit(CurrenciesMutation.ShowContent(result.result) to null)
        }
        if (result.error != null) {
            emit(CurrenciesMutation.ShowContent(null) to exceptionHandler.parseException(result.error))
        }
    }
}