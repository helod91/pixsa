package com.roche.android.bpi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.roche.android.bpi.presentation.features.currencies.CurrenciesScreen
import com.roche.android.bpi.presentation.features.currencies.CurrenciesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CurrenciesScreenDestination() {
    val viewModel = getViewModel<CurrenciesViewModel>()

    CurrenciesScreen(
        state = viewModel.state.collectAsState().value,
        effects = viewModel.effect
    ) { event ->
        viewModel.onEvent(event)
    }
}