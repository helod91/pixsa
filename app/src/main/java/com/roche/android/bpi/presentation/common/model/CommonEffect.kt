package com.roche.android.bpi.presentation.common.model

import com.roche.android.bpi.presentation.common.arch.SideEffect

sealed interface CommonEffect : SideEffect {
    data class ShowErrorDialog(val messageRes: Int) : CommonEffect

    data class ShowSnackbar(val messageRes: Int) : CommonEffect

    sealed interface Navigation : CommonEffect {
        data object CloseScreen : Navigation
    }
}