package com.roche.android.bpi.presentation.common.arch

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roche.android.bpi.presentation.common.dispatcher.DispatcherProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <UiState : ViewState> viewModelDelegate(
    eventProcessors: Collection<EventProcessor>,
    reducers: Collection<Reducer<UiState>>,
    dispatcherProvider: DispatcherProvider,
    initialState: UiState
) = ViewModelDelegateProperty(
    eventProcessors,
    reducers,
    dispatcherProvider,
    mutableStateOf(initialState)
)

class ViewModelDelegateProperty<UiState : ViewState>(
    private val eventProcessors: Collection<EventProcessor>,
    private val reducers: Collection<Reducer<UiState>>,
    private val dispatcherProvider: DispatcherProvider,
    private val viewMutableState: MutableState<UiState>
) : ReadOnlyProperty<ViewModel, ViewModelDelegate<UiState>> {
    override fun getValue(
        thisRef: ViewModel,
        property: KProperty<*>,
    ): ViewModelDelegate<UiState> =
            ViewModelDelegate(
            eventProcessors = eventProcessors,
            reducers = reducers,
            coroutineScope = thisRef.viewModelScope,
            dispatcherProvider = dispatcherProvider,
            viewMutableState = viewMutableState
        )
}