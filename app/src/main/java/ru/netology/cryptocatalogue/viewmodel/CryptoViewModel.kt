package ru.netology.cryptocatalogue.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.netology.cryptocatalogue.dto.CryptoCoin
import ru.netology.cryptocatalogue.dto.CryptoLoadingState
import ru.netology.cryptocatalogue.repository.CryptoRepository

class CryptoViewModel(private val repository: CryptoRepository): ViewModel() {

private val _state = MutableStateFlow(CryptoLoadingState())
    val state: StateFlow<CryptoLoadingState> = _state

    private val _coins = MutableStateFlow<List<CryptoCoin>>(emptyList())
    val coins: StateFlow<List<CryptoCoin>> = _coins

init {
    loadCoinsList()
}

    fun loadCoinsList() {
        viewModelScope.launch {
            _state.value = CryptoLoadingState(loading = true)

        try {
            val coins = repository.getCoinsList()
            _coins.value = coins
            _state.value = CryptoLoadingState()

        } catch (_: Exception) {
            _state.value = CryptoLoadingState(error = true)
        }

        }
    }
}