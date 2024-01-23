package com.roche.android.bpi.presentation.features.currencies

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.roche.android.bpi.domain.entity.BitcoinCurrencyResult
import com.roche.android.bpi.presentation.common.arch.EventProcessor
import com.roche.android.bpi.presentation.common.arch.Mutation
import com.roche.android.bpi.presentation.common.arch.Reducer
import com.roche.android.bpi.presentation.common.arch.SideEffect
import com.roche.android.bpi.presentation.common.arch.ViewEvent
import com.roche.android.bpi.presentation.common.arch.ViewState
import com.roche.android.bpi.presentation.common.arch.model
import com.roche.android.bpi.presentation.common.dispatcher.DispatcherProvider
import com.roche.android.bpi.presentation.common.model.CommonState
import kotlinx.coroutines.channels.Channel
import org.koin.core.component.KoinComponent

class CurrenciesViewModel(
    eventProcessors: Collection<EventProcessor>,
    reducers: Collection<Reducer>,
    dispatcherProvider: DispatcherProvider
) : ViewModel(), KoinComponent {

    private val model by model(
        eventProcessors,
        reducers,
        dispatcherProvider,
        CommonState.Loading
    )

    internal val state: State<ViewState> get() = model.viewState
    internal val effect: Channel<SideEffect> get() = model.effect

    init {
        model.process(CurrenciesEvent.Refresh)
    }

    fun onEvent(event: ViewEvent) = model.process(event)
}

sealed interface CurrenciesState : ViewState {
    data class Content(val currencies: BitcoinCurrencyResult?) : CurrenciesState
}

sealed interface CurrenciesMutation : Mutation {
    data class ShowContent(val currencies: BitcoinCurrencyResult?) : CurrenciesMutation
}

sealed interface CurrenciesEvent : ViewEvent {
    data object Refresh : CurrenciesEvent
    data object Retry : CurrenciesEvent
}