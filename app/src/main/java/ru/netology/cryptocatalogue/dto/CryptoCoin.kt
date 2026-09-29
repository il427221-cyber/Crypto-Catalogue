package ru.netology.cryptocatalogue.dto

data class CryptoCoin (
    val id: String = "",
    val symbol: String = "",
    val name: String = "",
    val price_usd: String? = "",
    val percent_change_24h: String? = ""
)

