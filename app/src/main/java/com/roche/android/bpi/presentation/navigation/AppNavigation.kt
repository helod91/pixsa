package com.roche.android.bpi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.roche.android.bpi.presentation.features.currencies.CurrenciesScreenDestination
import com.roche.android.bpi.presentation.features.home.HomeNavScreen
import com.roche.android.bpi.presentation.features.home.HomeTabsScreen
import com.roche.android.bpi.presentation.features.router.RouterScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigation.Routes.ROUTER
    ) {
        composable(
            route = Navigation.Routes.ROUTER
        ) {
            RouterScreen(navController)
        }
        composable(
            route = Navigation.Routes.HOME_TABS
        ) {
            HomeTabsScreen()
        }
        composable(
            route = Navigation.Routes.HOME_NAV
        ) {
            HomeNavScreen()
        }
        composable(
            route = Navigation.Routes.CURRENCIES
        ) {
            CurrenciesScreenDestination(navController)
        }
    }
}

object Navigation {
    object Routes {
        const val ROUTER = "router"
        const val HOME_TABS = "home_tabs"
        const val HOME_NAV = "home_nav"
        const val CURRENCIES = "currencies"
    }
}