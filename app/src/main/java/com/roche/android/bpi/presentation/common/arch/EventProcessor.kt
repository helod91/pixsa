package com.roche.android.bpi.presentation.common.arch

import kotlinx.coroutines.flow.Flow

interface EventProcessor {

    operator fun invoke(event: ViewEvent): Flow<Pair<Mutation?, SideEffect?>>
}