package com.roche.android.bpi.presentation.features.currencies

import com.roche.android.bpi.domain.entity.BitcoinCurrencyResult
import com.roche.android.bpi.presentation.common.arch.Mutation
import com.roche.android.bpi.presentation.common.arch.Reducer

class CurrenciesReducer : Reducer<CurrenciesState> {
    override fun invoke(mutation: Mutation, currentState: CurrenciesState): CurrenciesState? =
        if (mutation is CurrenciesMutation) {
            when (mutation) {
                is CurrenciesMutation.ShowLoadContent ->
                    currentState.mutateToShowContentLoading()

                is CurrenciesMutation.ShowContent ->
                    currentState.mutateToShowContent(mutation.currencies)
            }
        } else {
            null
        }

    private fun CurrenciesState.mutateToShowContentLoading() =
        copy(contentLoading = true)

    private fun CurrenciesState.mutateToShowContent(currencies: BitcoinCurrencyResult?) =
        copy(
            contentLoading = false,
            content = currencies
        )
}