package com.roche.android.bpi.presentation.common

import com.roche.android.bpi.presentation.common.arch.EventProcessor
import com.roche.android.bpi.presentation.common.arch.Mutation
import com.roche.android.bpi.presentation.common.arch.SideEffect
import com.roche.android.bpi.presentation.common.arch.ViewEvent
import com.roche.android.bpi.presentation.common.model.CommonEffect
import com.roche.android.bpi.presentation.common.model.CommonEvent
import com.roche.android.bpi.presentation.common.model.NoMutation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CommonNavigationEventProcessor : EventProcessor {
    override fun invoke(event: ViewEvent): Flow<Pair<NoMutation?, CommonEffect?>> =
        flow {
            if (event is CommonEvent) {
                when (event) {
                    CommonEvent.BackClick -> {
                        emit(null to CommonEffect.Navigation.CloseScreen)
                    }
                }
            }
        }
}
