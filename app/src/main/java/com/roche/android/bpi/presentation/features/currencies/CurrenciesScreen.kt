package com.roche.android.bpi.presentation.features.currencies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roche.android.bpi.R
import com.roche.android.bpi.domain.entity.BitcoinCurrency
import com.roche.android.bpi.domain.entity.BitcoinCurrencyResult
import com.roche.android.bpi.presentation.common.arch.SideEffect
import com.roche.android.bpi.presentation.common.arch.ViewEvent
import com.roche.android.bpi.presentation.common.model.CloseScreen
import com.roche.android.bpi.presentation.common.view.handleActivityCompletion
import com.roche.android.bpi.presentation.common.view.handleNetworkErrorEffect
import com.roche.android.bpi.presentation.common.view.handleUnknownErrorEffect
import com.roche.android.bpi.presentation.theme.SIDE_EFFECT_KEY
import kotlinx.coroutines.channels.Channel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CurrenciesScreen(
    state: CurrenciesState,
    effects: Channel<SideEffect>?,
    onEvent: (event: ViewEvent) -> Unit,
    onNavigation: (navigationEffect: SideEffect) -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.contentLoading,
        onRefresh = { onEvent(CurrenciesEvent.Refresh) }
    )

    LaunchedEffect(SIDE_EFFECT_KEY) {
        if (effects != null) {
            for (effect in effects) {
                if (effect is CloseScreen) {
                    onNavigation(effect)
                }
                handleNetworkErrorEffect(effect)
                handleUnknownErrorEffect(effect)
                handleActivityCompletion(effect)

                if (effect is CurrenciesEffect) {
                    when (effect) {
                        CurrenciesEffect.CurrenciesFailedToLoad -> TODO()
                        CurrenciesEffect.CurrenciesLoaded -> TODO()
                    }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = { CurrenciesTopBar(onEvent) },
    ) {
        Box(
            modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .pullRefresh(pullRefreshState)
        ) {
            Column(Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    text = stringResource(R.string.title_bpi)
                )

                Spacer(Modifier.height(16.dp))

                Divider()

                Spacer(Modifier.height(16.dp))

                if (state.content != null) {
                    CurrenciesList(state.content)
                }

            }

            PullRefreshIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                refreshing = state.contentLoading,
                state = pullRefreshState
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CurrenciesTopBar(onEvent: (event: CurrenciesEvent) -> Unit) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        actions = {
            IconButton(onClick = { onEvent(CurrenciesEvent.Refresh) }) {
                Icon(Icons.Filled.Refresh, null)
            }
        }
    )
}

@Composable
private fun CurrenciesList(bitcoinCurrencyResult: BitcoinCurrencyResult) {
    val displayUpdateDateFormat =
        SimpleDateFormat("EEE, d MMM yyyy HH:mm", Locale.getDefault())
    val updatedDateValue = displayUpdateDateFormat.format(bitcoinCurrencyResult.updated)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.Top,
        userScrollEnabled = true
    ) {
        item {
            Text("Updated at: $updatedDateValue")

            Spacer(Modifier.height(16.dp))
        }

        items(
            items = bitcoinCurrencyResult.currencies,
            itemContent = { currency ->
                Row {
                    Text("${currency.currency}: ")

                    Spacer(Modifier.width(8.dp))

                    Text(currency.rate)
                }

                Spacer(Modifier.height(4.dp))
            }
        )
    }
}

@Preview
@Composable
fun SuccessCurrenciesScreenPreview() {
    val currencies = arrayListOf(
        BitcoinCurrency("33", "-", "EUR"),
        BitcoinCurrency("44", "-", "USD"),
        BitcoinCurrency("55", "-", "GBP")
    )
    val result = BitcoinCurrencyResult(currencies, Date())

    CurrenciesScreen(
        state = CurrenciesState(content = result),
        effects = null,
        {},
        {}
    )
}