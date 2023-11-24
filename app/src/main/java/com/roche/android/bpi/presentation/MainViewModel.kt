package com.roche.android.bpi.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roche.android.bpi.domain.entity.Data
import com.roche.android.bpi.domain.entity.currencyprice.BpiPriceInfo
import com.roche.android.bpi.domain.usecase.GetBitcoinCurrentPriceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel(
    private val getBitcoinCurrentPriceUseCase: GetBitcoinCurrentPriceUseCase
) : ViewModel(), KoinComponent {

    private val _bpiData = MutableLiveData<Data<BpiPriceInfo>>()
    val bpiData: LiveData<Data<BpiPriceInfo>>
        get() = _bpiData

    fun loadCurrentPrices() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getBitcoinCurrentPriceUseCase.execute()
            _bpiData.postValue(result)
        }
    }
}