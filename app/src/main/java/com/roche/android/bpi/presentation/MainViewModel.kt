package com.roche.android.bpi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roche.android.bpi.domain.entity.BitcoinCurrency
import com.roche.android.bpi.domain.entity.Data
import com.roche.android.bpi.domain.usecase.GetBitcoinCurrentPriceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class MainViewModel(
    private val getBitcoinCurrentPriceUseCase: GetBitcoinCurrentPriceUseCase
) : ViewModel(), KoinComponent {

    private val _bpiData = MutableLiveData<Data<HashMap<String, BitcoinCurrency>>>()
    val bpiData: LiveData<Data<HashMap<String, BitcoinCurrency>>>
        get() = _bpiData

    fun loadCurrentPrices() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getBitcoinCurrentPriceUseCase.execute()
            _bpiData.postValue(result)
        }
    }
}