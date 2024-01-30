package com.roche.android.bpi.presentation.features.currencies

import com.roche.android.bpi.domain.entity.BitcoinCurrencyResult
import com.roche.android.bpi.presentation.common.arch.Mutation
import com.roche.android.bpi.presentation.common.arch.SideEffect
import com.roche.android.bpi.presentation.common.arch.ViewEvent
import com.roche.android.bpi.presentation.common.arch.ViewState

data class CurrenciesState(
        val content: BitcoinCurrencyResult? = null,
        val contentLoading: Boolean = false
) : ViewState

sealed interface CurrenciesMutation : Mutation {
    data object ShowLoadContent : CurrenciesMutation
    data class ShowContent(val currencies: BitcoinCurrencyResult?) : CurrenciesMutation
}

sealed interface CurrenciesEvent : ViewEvent {
    data object Refresh : CurrenciesEvent
    data object Retry : CurrenciesEvent
}

sealed interface CurrenciesEffect : SideEffect {
    data object CurrenciesLoaded : CurrenciesEffect

    data object CurrenciesFailedToLoad : CurrenciesEffect
}