package com.roche.android.bpi.presentation.common.view

import com.roche.android.bpi.presentation.common.arch.SideEffect
import com.roche.android.bpi.presentation.common.model.ActivityCompletion
import com.roche.android.bpi.presentation.common.model.NetworkErrorEffect
import com.roche.android.bpi.presentation.common.model.ShowUnknownErrorDialog

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
        //show snak
        //show confetti
    }
}