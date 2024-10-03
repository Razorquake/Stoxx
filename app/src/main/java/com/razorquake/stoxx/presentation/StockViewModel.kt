package com.razorquake.stoxx.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.razorquake.stoxx.data.remote.StockApi
import com.razorquake.stoxx.data.remote.dto.StockQuoteResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(
    private val stockApi: StockApi
) : ViewModel() {

    private val _stockQuote = MutableStateFlow<StockQuoteResponse?>(null)
    val stockQuote: StateFlow<StockQuoteResponse?> = _stockQuote

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getStockQuote(symbol: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val response = stockApi.getStockQuote(symbol = symbol)
                if (response.globalQuote.symbol.isEmpty()) {
                    _error.value = "Invalid stock symbol"
                } else {
                    _stockQuote.value = response
                }
            } catch (e: Exception) {
                _error.value = "Error fetching stock data"
            } finally {
                _loading.value = false
            }
        }
    }
}