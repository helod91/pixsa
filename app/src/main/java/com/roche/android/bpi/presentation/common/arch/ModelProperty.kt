package com.roche.android.bpi.presentation.common.arch

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roche.android.bpi.presentation.common.dispatcher.DispatcherProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun model(
    eventProcessors: Collection<EventProcessor>,
    reducers: Collection<Reducer>,
    dispatcherProvider: DispatcherProvider,
    initialState: ViewState
) = ModelProperty(
    eventProcessors,
    reducers,
    dispatcherProvider,
    mutableStateOf(initialState)
)

class ModelProperty(
    private val eventProcessors: Collection<EventProcessor>,
    private val reducers: Collection<Reducer>,
    private val dispatcherProvider: DispatcherProvider,
    private val viewMutableState: MutableState<ViewState>
) : ReadOnlyProperty<ViewModel, Model> {
    override fun getValue(
        thisRef: ViewModel,
        property: KProperty<*>,
    ): Model =
        Model(
            eventProcessors = eventProcessors,
            reducers = reducers,
            coroutineScope = thisRef.viewModelScope,
            dispatcherProvider = dispatcherProvider,
            viewMutableState = viewMutableState
        )
}