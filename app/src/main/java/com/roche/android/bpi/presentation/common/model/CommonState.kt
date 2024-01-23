package com.roche.android.bpi.presentation.common.model

import com.roche.android.bpi.presentation.common.arch.ViewState

sealed interface CommonState : ViewState {
    data object Loading : CommonState
}