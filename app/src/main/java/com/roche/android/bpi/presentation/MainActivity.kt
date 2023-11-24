package com.roche.android.bpi.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.roche.android.bpi.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.bpiData.observe(this) { result ->
            result.result?.let { priceData ->
                binding.tvCurrencyEur.text = priceData["EUR"]?.rate
                binding.tvCurrencyGbp.text = priceData["GBP"]?.rate
                binding.tvCurrencyUsd.text = priceData["USD"]?.rate
            }
        }
        viewModel.loadCurrentPrices()
    }
}