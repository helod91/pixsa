package com.roche.android.bpi.presentation.common

import com.roche.android.bpi.presentation.common.arch.EventProcessor
import com.roche.android.bpi.presentation.common.arch.Mutation
import com.roche.android.bpi.presentation.common.arch.SideEffect
import com.roche.android.bpi.presentation.common.arch.ViewEvent
import com.roche.android.bpi.presentation.common.model.BackClick
import com.roche.android.bpi.presentation.common.model.CloseScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CommonNavigationEventProcessor : EventProcessor {
    override fun invoke(event: ViewEvent): Flow<Pair<Mutation?, SideEffect?>> =
        flow {
            if (event is BackClick) {
                emit(null to CloseScreen)
            }
        }
}
