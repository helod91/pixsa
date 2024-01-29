package com.roche.android.bpi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.roche.android.bpi.presentation.common.model.CloseScreen
import com.roche.android.bpi.presentation.features.currencies.CurrenciesScreen
import com.roche.android.bpi.presentation.features.currencies.CurrenciesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CurrenciesScreenDestination(navController: NavController) {
    val viewModel = getViewModel<CurrenciesViewModel>()
    val state by remember { viewModel.state }

    CurrenciesScreen(
        state = state,
        effects = viewModel.effect,
        onEvent = { event ->
            viewModel.onEvent(event)
        },
        onNavigation = { effect ->
            if (effect is CloseScreen) {
                navController.popBackStack()
            }
            //TODO handle other navigation effects
        }
    )
}