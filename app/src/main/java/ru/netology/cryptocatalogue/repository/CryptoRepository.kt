package ru.netology.cryptocatalogue.repository

import ru.netology.cryptocatalogue.dto.CryptoCoin
import ru.netology.cryptocatalogue.dto.CryptoCoinDetail

interface CryptoRepository {
    suspend fun getCoinsList(): List<CryptoCoin>
    suspend fun getCoinDetail(id: String): CryptoCoinDetail
}