package com.roche.android.bpi.presentation.features.currencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roche.android.bpi.domain.entity.BitcoinCurrencyResult
import com.roche.android.bpi.domain.usecase.GetBitcoinCurrentPriceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class CurrenciesViewModel(
    private val getBitcoinCurrentPriceUseCase: GetBitcoinCurrentPriceUseCase
) : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(CurrenciesState())
    val state: StateFlow<CurrenciesState> = _state

    private val _effect: Channel<CurrenciesEffect> = Channel()
    val effect = _effect

    init {
        loadCurrentPrices()
    }

    fun loadCurrentPrices() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { CurrenciesState(loading = true) }

            val result = getBitcoinCurrentPriceUseCase()

            _state.update {
                CurrenciesState(
                    result.result,
                    result.error
                )
            }
            if (result.error != null) {
                _effect.send(CurrenciesEffect.DataLoadingError)
            }
            if (result.result != null) {
                _effect.send(CurrenciesEffect.DataLoadedSuccessfully)
            }
        }
    }

    fun onEvent(event: CurrenciesEvent) {
        when (event) {
            is CurrenciesEvent.Refresh, CurrenciesEvent.Retry -> loadCurrentPrices()
        }
    }

}

data class CurrenciesState(
    val currencies: BitcoinCurrencyResult? = null,
    val error: Throwable? = null,
    val loading: Boolean = false
)

sealed class CurrenciesEvent {
    data object Refresh : CurrenciesEvent()
    data object Retry : CurrenciesEvent()
}

sealed class CurrenciesEffect {
    data object DataLoadedSuccessfully : CurrenciesEffect()
    data object DataLoadingError : CurrenciesEffect()
}