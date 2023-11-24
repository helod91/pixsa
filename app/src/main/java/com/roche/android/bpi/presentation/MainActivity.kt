package com.roche.android.bpi.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.roche.android.bpi.R
import com.roche.android.bpi.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        viewModel.bpiData.observe(this) { priceData ->
            binding.tvCurrencyEur.text = priceData.bpi.EUR.rate
            binding.tvCurrencyGbp.text = priceData.bpi.GBP.rate
            binding.tvCurrencyUsd.text = priceData.bpi.USD.rate
        }
    }
}