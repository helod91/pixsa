package com.roche.android.bpi.presentation.common.model

import com.roche.android.bpi.presentation.common.arch.Mutation

sealed interface CommonMutation : Mutation {
    data object ShowLoader : CommonMutation
}

interface NoMutation : Mutation