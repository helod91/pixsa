package com.roche.android.bpi.presentation.common.arch

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import com.roche.android.bpi.presentation.common.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch

class Model(
    private val eventProcessors: Collection<EventProcessor>,
    private val reducers: Collection<Reducer>,
    private val coroutineScope: CoroutineScope,
    private val dispatcherProvider: DispatcherProvider,
    private val viewMutableState: MutableState<ViewState>
) {
    val viewState: State<ViewState> = viewMutableState
    val effect: Channel<SideEffect> = Channel(Channel.BUFFERED)

    fun process(event: ViewEvent) {
        coroutineScope.launch(dispatcherProvider.default) {
            eventProcessors.map { eventProcessor -> eventProcessor(event) }
                .merge()
                .collect { (mutation, event) ->
                    mutation?.let(::handleMutation)
                    event?.let(effect::trySend)
                }
        }
    }

    private fun handleMutation(mutation: Mutation) {
        reducers.asIterable()
            .forEach { reducer ->
                reducer(mutation)?.let { state ->
                    coroutineScope.launch(dispatcherProvider.ui) {
                        viewMutableState.value = state
                    }
                }
            }
    }
}

interface ViewState

interface ViewEvent

interface SideEffect

interface Mutation