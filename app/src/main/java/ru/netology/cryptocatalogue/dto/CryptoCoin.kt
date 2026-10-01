package ru.netology.cryptocatalogue.dto

data class CryptoCoin (
    val id: String = "",
    val symbol: String = "",
    val name: String = "",
    val price_usd: String? = null,
    val percent_change_24h: String? = null
)

data class CryptoCoinDetail(
    val id: String = "",
    val symbol: String = "",
    val name: String = "",
    val rank: Int = 0,
    val price_usd: String? = null,
    val percent_change_24h: String? = null,
    val percent_change_1h: String? = null,
    val percent_change_7d: String? = null,
    val volume24: Double? = null
)