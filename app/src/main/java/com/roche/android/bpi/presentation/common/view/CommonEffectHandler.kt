package com.roche.android.bpi.presentation.common.view

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import com.roche.android.bpi.presentation.common.arch.SideEffect
import com.roche.android.bpi.presentation.common.model.ActivityCompletion
import com.roche.android.bpi.presentation.common.model.CloseScreen
import com.roche.android.bpi.presentation.common.model.CommonEffect
import com.roche.android.bpi.presentation.common.model.NetworkErrorEffect
import com.roche.android.bpi.presentation.common.model.ShowUnknownErrorDialog

fun handleCommonEffect(
    effect: SideEffect,
    snackbarHostState: SnackbarHostState,
) {
    if (effect is CommonEffect) {
        when (effect) {
            is CommonEffect.ShowDialog -> TODO()
            is CommonEffect.ShowSnackbar -> TODO()
        }
    }
}

fun handleNetworkErrorEffect(
    effect: SideEffect
) {
    if (effect is NetworkErrorEffect) {
        when (effect) {
            NetworkErrorEffect.ShowForbiddenDialog -> TODO()
            NetworkErrorEffect.ShowTimeoutDialog -> TODO()
            NetworkErrorEffect.ShowUnauthorizedDialog -> TODO()
        }
    }
}

fun handleUnknownErrorEffect(
    effect: SideEffect
) {
    if (effect is ShowUnknownErrorDialog) {
        TODO()
    }
}

fun handleActivityCompletion(
    effect: SideEffect
) {
    if (effect is ActivityCompletion) {
        TODO()
    }
}