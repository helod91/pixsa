package com.roche.android.bpi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigation.Routes.CURRENCIES
    ) {
        composable(
            route = Navigation.Routes.CURRENCIES
        ) {
            CurrenciesScreenDestination()
        }
    }
}

object Navigation {
    object Routes {
        const val CURRENCIES = "currencies"
    }
}