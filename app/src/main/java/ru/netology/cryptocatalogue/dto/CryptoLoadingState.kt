package ru.netology.cryptocatalogue.dto


data class CoinListResponse(
    val data: List<CryptoCoin> = emptyList()
)

data class CryptoLoadingState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val fromCache: Boolean = false
)