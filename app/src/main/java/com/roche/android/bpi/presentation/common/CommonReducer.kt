package com.roche.android.bpi.presentation.common

import com.roche.android.bpi.presentation.common.arch.Mutation
import com.roche.android.bpi.presentation.common.arch.Reducer
import com.roche.android.bpi.presentation.common.arch.ViewState
import com.roche.android.bpi.presentation.common.model.CommonMutation
import com.roche.android.bpi.presentation.common.model.CommonState

class CommonReducer : Reducer {
    override fun invoke(mutation: Mutation): ViewState? =
        if (mutation is CommonMutation) {
            when (mutation) {
                is CommonMutation.ShowLoader -> CommonState.Loading
            }
        } else {
            null
        }
}

