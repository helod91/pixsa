package com.roche.android.bpi.presentation.common.view

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import com.roche.android.bpi.presentation.common.arch.SideEffect
import com.roche.android.bpi.presentation.common.model.CommonEffect

fun handleCommonEffect(
    effect: SideEffect,
    snackbarHostState: SnackbarHostState,
    onNavigation: (navigationEffect: SideEffect) -> Unit
) {
    if (effect is CommonEffect) {
        when (effect) {
            is CommonEffect.Navigation.CloseScreen -> onNavigation(effect)
            is CommonEffect.ShowErrorDialog -> TODO()
            is CommonEffect.ShowSnackbar -> TODO()
        }
    }
}

fun handleCommonNavigation(effect: SideEffect, navController: NavController) {
    if (effect is CommonEffect.Navigation) {
        when (effect) {
            CommonEffect.Navigation.CloseScreen -> navController.popBackStack()
        }
    }
}