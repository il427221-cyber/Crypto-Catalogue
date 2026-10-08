package ru.netology.cryptocatalogue.repository

import ru.netology.cryptocatalogue.dto.CryptoCoin
import ru.netology.cryptocatalogue.dto.CryptoCoinDetail
import ru.netology.cryptocatalogue.dto.ResultState

interface CryptoRepository {
    suspend fun getCoinsList(): ResultState<List<CryptoCoin>>

//    suspend fun getCoinDetail(id: String): CryptoCoinDetail
}