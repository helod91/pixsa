package com.roche.android.bpi.presentation.common.model

import com.roche.android.bpi.presentation.common.arch.SideEffect

sealed interface CommonEffect : SideEffect {
    data class ShowDialog(val messageRes: Int) : CommonEffect

    data class ShowSnackbar(val messageRes: Int) : CommonEffect
}

data object CloseScreen : SideEffect

data object ShowUnknownErrorDialog : SideEffect

sealed interface NetworkErrorEffect : SideEffect {
    data object ShowUnauthorizedDialog : NetworkErrorEffect
    data object ShowForbiddenDialog : NetworkErrorEffect
    data object ShowTimeoutDialog : NetworkErrorEffect
    //etc
}

data object ActivityCompletion : SideEffect