package com.roche.android.bpi.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.roche.android.bpi.domain.entity.BpiPriceInfo
import com.roche.android.bpi.domain.usecase.GetBitcoinCurrentPriceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel(
    private val app: Application
) : AndroidViewModel(app), KoinComponent {

    private val getBitcoinCurrentPriceUseCase: GetBitcoinCurrentPriceUseCase by inject()

    private val _bpiData = MutableLiveData<BpiPriceInfo>()
    val bpiData: LiveData<BpiPriceInfo>
        get() = _bpiData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getBitcoinCurrentPriceUseCase.execute()
            _bpiData.postValue(result)
        }
    }
}