package com.roche.android.bpi.presentation.features.currencies

import com.roche.android.bpi.presentation.common.arch.Mutation
import com.roche.android.bpi.presentation.common.arch.Reducer
import com.roche.android.bpi.presentation.common.arch.ViewState

class CurrenciesReducer : Reducer {
    override fun invoke(mutation: Mutation): ViewState? =
        if (mutation is CurrenciesMutation) {
            when (mutation) {
                is CurrenciesMutation.ShowContent -> CurrenciesState.Content(mutation.currencies)
            }
        } else {
            null
        }
}