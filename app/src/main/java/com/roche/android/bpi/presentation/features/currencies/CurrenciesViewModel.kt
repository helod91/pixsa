package com.roche.android.bpi.presentation.features.currencies

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.roche.android.bpi.presentation.common.arch.EventProcessor
import com.roche.android.bpi.presentation.common.arch.Reducer
import com.roche.android.bpi.presentation.common.arch.SideEffect
import com.roche.android.bpi.presentation.common.arch.ViewEvent
import com.roche.android.bpi.presentation.common.arch.viewModelDelegate
import com.roche.android.bpi.presentation.common.dispatcher.DispatcherProvider
import kotlinx.coroutines.channels.Channel
import org.koin.core.component.KoinComponent

class CurrenciesViewModel(
    eventProcessors: Collection<EventProcessor>,
    reducers: Collection<Reducer<CurrenciesState>>,
    dispatcherProvider: DispatcherProvider
) : ViewModel(), KoinComponent {

    private val viewModelDelegate by viewModelDelegate(
        eventProcessors,
        reducers,
        dispatcherProvider,
        CurrenciesState()
    )

    internal val state: State<CurrenciesState> get() = viewModelDelegate.viewState
    internal val effect: Channel<SideEffect> get() = viewModelDelegate.effect

    init {
        viewModelDelegate.process(CurrenciesEvent.Refresh)
    }

    fun onEvent(event: ViewEvent) = viewModelDelegate.process(event)
}
